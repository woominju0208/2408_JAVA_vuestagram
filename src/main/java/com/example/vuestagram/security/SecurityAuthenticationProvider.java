package com.example.vuestagram.security;


import com.example.vuestagram.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationProvider {
    private final JwtUtil jwtUtil;

    // Spring Security에서 사용자의 인증 정보를 담는 객체 생성하는 메소드
    public Authentication authenticate(String token) {
        // UsernamePasswordAuthenticationToken(principal, credentials, authorities)
        // 각 파라미터는 인증 된 사용자 객체, 비밀번호 저장 여부,
        return new UsernamePasswordAuthenticationToken(jwtUtil.getClaims(token),null,null);
    }
}
