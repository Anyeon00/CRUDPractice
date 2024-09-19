package com.example.crudpractice.post.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateDTO {
    private Long id;
    private String title;
    private String content;

    @Builder
    private PostUpdateDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
