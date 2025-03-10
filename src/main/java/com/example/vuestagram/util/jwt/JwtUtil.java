package com.example.vuestagram.util.jwt;

import com.example.vuestagram.model.User;
import com.example.vuestagram.util.jwt.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;

        // Base64 인코딩 된 Secret을 디코딩 하여 Key 객체로 변환
        // HMAC 서명에 필요한 적절할 SecretKey를 제공하기 위한 것
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.getSecret()));
    }

    // 액세스 토큰 생성 메소드
    public String generateAccessToken(User user) {
        return this.generateToken(user.getUserId(), jwtConfig.getAccessTokenExpiry());
    }

    // 리프레시 토큰 생성 메소드
    public String generateRefreshToken(User user) {
        return this.generateToken(user.getUserId(), jwtConfig.getRefreshTokenExpiry());
    }

    // 토큰 생성 메소드
    public String generateToken(Long userId, int expiry) {
        Date now = new Date(); // 현재 시간

//        JJWT 의 경우 header와 payload, secretKey를 셋팅하면 signature는 자동으로 생성해 줌
        return Jwts.builder()
                .header().type(jwtConfig.getType())
                .and()
                .setSubject(String.valueOf(userId))
                .setIssuer(jwtConfig.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiry))
                .signWith(secretKey)
                .compact();
    }
}
