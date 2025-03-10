package com.example.vuestagram.util.jwt.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "config.jwt-config")
public class JwtConfig {
    private final String issuer;
    private final String type;
    private final int accessTokenExpiry;
    private final int refreshTokenExpiry;
    private final String refreshTokenCookieName;
    private final int refreshTokenCookieExpiry;  // 리프래시토큰 쿠키시간
    private final String secret;    // 암호화 번호
    private final String headerKey;
    private final String scheme;
    private final String reissUri;
}
