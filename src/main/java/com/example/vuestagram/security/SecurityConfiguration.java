package com.example.vuestagram.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 설정을 활성화, 5.7버전 이상에서는 생략 가능 5.6버전 이하부터는 안적어주면 실행자체를 안함(관습적으로 적어줌)
public class SecurityConfiguration {
    @Bean
    // 비밀번호 암호화 관련 구현체 정의 및 빈등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain fillerChain(HttpSecurity http) throws Exception {
//        세션로그인 부분 모두 비활성화하기/ () 파라미터 안은 내가 지어주면 됨
        return http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성화(JJWT를 사용할거기 때문)
                .httpBasic(h -> h.disable()) // SSR이 아니므로 화면 설정 비활성 설정
                .formLogin(form -> form.disable()) // SSR이 아니므로 폼로그인 기능 비활성 설정
                .csrf(csrf -> csrf.disable()) // SSR이 아니므로 CSRF 토큰 인증 비활성 설정

                .authorizeHttpRequests(request ->  // 리퀘스트에 대한 인가 체크 처리
                   request.requestMatchers("/api/login").permitAll()  // '/api/login'은 인가없이 접근가능
                           .anyRequest().authenticated()               // 위에서 정의한 것들은 이외에는 인가 필수
                )
                .build();
    }
}
