package com.htdinh.bookstore.jwt;

import com.htdinh.bookstore.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    // 1h
    private static final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000;
    // 7 day
    private static final long REFRESH_TOKEN_VALID_TIME = 24 * 60 * 60 * 1000 * 7;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String createJwtAccessToken(User user) {
        Claims claims = Jwts.claims();

        claims.put("id", user.getId());
        claims.put("username", user.getUsername());

        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME);

        return Jwts.builder()
                .setIssuer(String.valueOf(user.getId()))
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    public String createJwtRefreshToken(String userId) {
        Claims claims = Jwts.claims();
        claims.put("ID", userId);

        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_TIME);

        return Jwts.builder()
                .setIssuer(userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    protected Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

    protected boolean isAccessTokenValid(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    protected boolean isRefreshTokenValid(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
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
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJwt(token).getBody();
    }
}
