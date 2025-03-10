package com.example.vuestagram.controller;

import com.example.vuestagram.service.AuthService;
import com.example.vuestagram.util.jwt.config.JwtConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST API 컨트롤러
@RequestMapping("/api") // AuthController 안에 있는건 시작이 /api로 실행
@RequiredArgsConstructor  // 객체 인스턴스화를 자동으로 해준다.
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login") // get메소드 선언(laravel router와 같음)
    public String login() {
        return authService.login();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
