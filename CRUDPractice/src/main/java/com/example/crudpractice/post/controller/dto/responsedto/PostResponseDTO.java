package com.example.crudpractice.post.controller.dto.responsedto;

import com.example.crudpractice.post.entity.Post;
import lombok.*;

@Data
public class PostResponseDTO {
    private String title;
    private String content;
    private UserResponseDTO userResponseDTO;

    public static PostResponseDTO from(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.title = post.getTitle();
        dto.content = post.getContent();
        dto.userResponseDTO = UserResponseDTO.from(post.getUser());
        return dto;
    }
}
