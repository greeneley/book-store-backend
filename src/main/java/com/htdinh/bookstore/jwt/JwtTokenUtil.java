package com.htdinh.bookstore.jwt;

import com.htdinh.bookstore.exception.TokenExpiredException;
import com.htdinh.bookstore.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000; // 1 hour
    private static final long REFRESH_TOKEN_VALID_TIME = 24 * 60 * 60 * 1000 * 7; // 7 days

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String createJwtAccessToken(User user) {
        return createToken(user.getId(), user.getUsername(), ACCESS_TOKEN_VALID_TIME);
    }

    public String createJwtRefreshToken(String userId) {
        return createToken(userId, null, REFRESH_TOKEN_VALID_TIME);
    }

    private String createToken(Object id, String username, long validTime) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("id", id);
        claims.put("uuid", UUID.randomUUID());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + validTime);

        return Jwts.builder()
                .setIssuer(String.valueOf(id))
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isAccessTokenValid(String token) {
        return isTokenValid(token);
    }

    public boolean isRefreshTokenValid(String token) {
        return isTokenValid(token);
    }

    private boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    // extract username from toke
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Trích xuất thông tin hết hạn từ token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            LOGGER.error("Token has expired at: " + e.getClaims().getExpiration(), e);
            // You can extract more information from the exception if needed
            throw new TokenExpiredException("Token expired at: " + e.getClaims().getExpiration());
        }
    }
}
