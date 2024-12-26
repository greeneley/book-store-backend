package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.AuthRequest;
import com.htdinh.bookstore.dto.request.LogoutRequest;
import com.htdinh.bookstore.dto.request.RefreshTokenRequest;
import com.htdinh.bookstore.dto.request.RegisterRequest;
import com.htdinh.bookstore.dto.response.AuthResponse;
import com.htdinh.bookstore.dto.response.RefreshTokenResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.jwt.JwtTokenUtil;
import com.htdinh.bookstore.model.RefreshToken;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.RefreshTokenRepository;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.AuthService;
import com.htdinh.bookstore.service.EmailService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        validateUserRegistration(request);
        User user = createUserFromRequest(request);
        userRepository.save(user);

        Context context = new Context();
        context.setVariable("name", user.getFirstName());
        context.setVariable("URL", "http://localhost:8082/verify?code=" + user.getVerificationCode());
        emailService.sendEmail(user.getEmail(), "Please verify your registration", "email-template.html", context);
        return "User created successfully";
    }

    @Override
    public String resend(String email) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email = " + email + " not found"));

        Context context = new Context();
        context.setVariable("name", user.getFirstName());
        context.setVariable("URL", "http://localhost:8082/verify?code=" + user.getVerificationCode());
        emailService.sendEmail(user.getEmail(), "Please verify your registration", "email-template.html", context);
        return "Resend email for signup successfully";
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.createJwtAccessToken(user);
        String generatedRefreshToken = jwtUtil.createJwtRefreshToken(String.valueOf(user.getId()));

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setValue(generatedRefreshToken);
        refreshToken.setExpireDt(convertDateToString(jwtUtil.extractExpiration(generatedRefreshToken)));
        refreshToken.setCrtDt(getCurrentTimestamp());
        refreshTokenRepository.save(refreshToken);

        return new AuthResponse(user.getId(), user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName(), accessToken, generatedRefreshToken);
    }

    @Transactional
    public String logout(LogoutRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new ResourceNotFoundException("User with ID = " + request.getId() + " not found"));
        refreshTokenRepository.deleteByUserId(user.getId());
        return "logout successfully";
    }

    @Override
    @Transactional
    public RefreshTokenResponse generateNewToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByValue(request.getRefreshToken()).orElseThrow(() -> new ResourceNotFoundException("Refresh token with value = " + request.getRefreshToken() + " not found"));

        User user = refreshToken.getUser();

        Long requestId = request.getId();
        if (!requestId.equals(user.getId())) {
            throw new BadCredentialsException("Not authorization");
        } else {
            refreshTokenRepository.deleteByValue(request.getRefreshToken());

            String accessToken = jwtUtil.createJwtAccessToken(user);
            String generatedRefreshToken = jwtUtil.createJwtRefreshToken(String.valueOf(user.getId()));

            RefreshToken newRefreshToken = new RefreshToken();
            newRefreshToken.setUser(user);
            newRefreshToken.setValue(generatedRefreshToken);
            newRefreshToken.setExpireDt(convertDateToString(jwtUtil.extractExpiration(generatedRefreshToken)));
            newRefreshToken.setCrtDt(getCurrentTimestamp());
            refreshTokenRepository.save(newRefreshToken);
            return RefreshTokenResponse.builder().accessToken(accessToken).refreshToken(generatedRefreshToken).build();
        }
    }

    private void validateUserRegistration(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
    }

    private User createUserFromRequest(RegisterRequest request) {
        User user = new User();
        user.setId(generateNewUserId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUserType("B");
        user.setBirthDay(request.getBirthday());
        user.setCrtDt(getCurrentTimestamp());
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setIsActive(false);
        return user;
    }

    private Long generateNewUserId() {
        return userRepository.getMaxId() + 1;
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }

    private String convertDateToString(Date input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(input);
    }
}
