package com.example.crudpractice.post.controller.dto.responsedto;

import com.example.crudpractice.user.entity.User;
import lombok.*;

@Data
public class UserResponseDTO {
    private String name;
    private String loginId;

    public static UserResponseDTO from(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.name = user.getName();
        dto.loginId = user.getLoginId();
        return dto;
    }
}
