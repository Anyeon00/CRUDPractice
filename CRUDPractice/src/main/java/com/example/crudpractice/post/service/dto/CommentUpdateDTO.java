package com.example.crudpractice.post.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateDTO {
    private Long id;
    private String content;

    @Builder
    private CommentUpdateDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
