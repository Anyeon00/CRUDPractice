package com.example.crudpractice.user.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdateDTO {
    private Long id;
    private String name;
    private String loginId;
    private String password;

    @Builder
    public UserUpdateDTO(Long id, String name, String loginId, String password) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}
