package com.htdinh.bookstore.service.impl;

import com.htdinh.bookstore.dto.request.ForgotPasswordRequest;
import com.htdinh.bookstore.dto.request.ProfileUpdateRequest;
import com.htdinh.bookstore.dto.request.ResetPasswordRequest;
import com.htdinh.bookstore.dto.response.ProfileUserResponse;
import com.htdinh.bookstore.exception.ResourceNotFoundException;
import com.htdinh.bookstore.mapper.ProfileUserMapper;
import com.htdinh.bookstore.model.User;
import com.htdinh.bookstore.repository.UserRepository;
import com.htdinh.bookstore.service.EmailService;
import com.htdinh.bookstore.service.UploadService;
import com.htdinh.bookstore.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProfileUserMapper profileUserMapper;

    @Autowired
    private UploadService uploadService;

    @Override
    public String verifyUser(String code) {
        User user = userRepository.findByVerificationCode(code).orElseThrow(() -> new ResourceNotFoundException("Code not exist:::" + code));
        if (user.getIsActive()) {
            return "verified";
        } else {
            user.setIsActive(true);
            userRepository.save(user);
            return "success";
        }
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email = " + email + " not found"));
        user.setResetPasswordToken(token);
        userRepository.save(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token).orElseThrow(() -> new ResourceNotFoundException("User with token = " + token + " not found"));
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public String processForgotPassword(ForgotPasswordRequest request) {
        String email = request.getEmail();
        String token = RandomString.make(30);
        updateResetPasswordToken(token, email);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email = " + email + " not found"));
        Context context = new Context();
        context.setVariable("name", user.getUsername());
        context.setVariable("URL", "http://localhost:8082/reset-password?code=" + token);
        emailService.sendEmail(user.getEmail(), "Bookstore - Password reset", "forgot-password.html", context);

        return "send email successfully";
    }

    @Override
    public String processResetPassword(ResetPasswordRequest request) {
        String token = request.getToken();
        String password = request.getPassword();

        User user = getByResetPasswordToken(token);
        updatePassword(user, password);
        return "You have successfully changed your password.";
    }

    @Override
    public ProfileUserResponse getProfileUser() {
        User user = getCurrentUser();
        return profileUserMapper.toProfileUser(user);
    }

    @Override
    @Transactional
    public String uploadAvatarProfile(MultipartFile multipartFile) throws Exception {
        String fileUrl = uploadService.uploadProfile(multipartFile, "profile");
        User user = getCurrentUser();
        user.setPhotos(fileUrl);
        userRepository.save(user);
        return "Upload successfully";
    }

    @Override
    public String updateProfileUser(ProfileUpdateRequest request) {
        User user = getCurrentUser();
        Optional.ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.getPhone()).ifPresent(user::setPhone);
        Optional.ofNullable(request.getBirthday()).ifPresent(user::setBirthDay);
        Optional.ofNullable(request.getNewPassword()).ifPresent(newPassword -> {
            user.setPassword(passwordEncoder.encode(newPassword));
        });

        user.setUpdtId(user.getId());
        user.setUpdtDt(getCurrentTimestamp());

        userRepository.save(user);
        return "update profile user successfully";
    }

    private LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
