package com.example.vuestagram.model;

import lombok.Getter;
import lombok.Setter;

// lombok 기능으로 제너레이트에 getter,setter 다 만들지 않고 @Getter,@Setter로 바로가능
@Getter
@Setter
public class User {
    private Long userId;
    private String name;
    private String account;
    private String password;
    private String profile;
    private String gender;
    private String refreshToken;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
}
