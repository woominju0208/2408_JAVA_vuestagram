package com.example.vuestagram.controller;

import com.example.vuestagram.dto.request.LoginRequestDTO;
import com.example.vuestagram.dto.response.ResponseBase;
import com.example.vuestagram.dto.response.ResponseLogin;
import com.example.vuestagram.service.AuthService;
import com.example.vuestagram.util.jwt.config.JwtConfig;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // REST API 컨트롤러
@RequestMapping("/api") // AuthController 안에 있는건 시작이 /api로 실행
@RequiredArgsConstructor  // 객체 인스턴스화를 자동으로 해준다.
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login") // get메소드 선언(laravel router와 같음)
    // 데이터타입은 return 값으로 ResponseEntity고 제너릭스로 값인 <ResponseBase>을 받고 그 안에 값으로 <ResponseLogin>을 받는다.
    public ResponseEntity<ResponseBase<ResponseLogin>> login(
            HttpServletResponse response
            ,@Valid @RequestBody LoginRequestDTO loginRequestDTO
    ) {
        ResponseLogin responseLogin = authService.login(loginRequestDTO, response);

        ResponseBase<ResponseLogin> responseBase = ResponseBase.<ResponseLogin>builder()
                .status(200)
                .message("로그인 성공")
                .data(responseLogin)
                .build();

        return ResponseEntity.status(200).body(responseBase);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
