package com.htdinh.bookstore.jwt;

import com.htdinh.bookstore.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    
    @Value("$(app.jwt.secret}")
    private String SECRET_KEY;
    
    public String generateAccessToken(User user) {
        return Jwts.builder().setSubject(String.format("%s", user.getUsername()))
                .setIssuer("DINH")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.ES512, SECRET_KEY)
                .compact();
    }
}
