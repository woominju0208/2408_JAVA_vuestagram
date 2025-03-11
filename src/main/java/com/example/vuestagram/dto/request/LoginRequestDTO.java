package com.example.vuestagram.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank(message = "아이디를 작성 해 주세요.")
    private String account;

    @NotBlank(message = "비밀번호를 작성 해 주세요.")
    private String password;
}
