package com.example.crudpractice.post.entity;

import com.example.crudpractice.post.service.dto.CommentUpdateDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue @Column(name = "comment_id")
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    private Comment(String content) {
        this.content = content;
    }

    //** 연관관계 편의 메서드 **//
    public void addPost(Post post) {    //addPost는 Post엔터티에서만 호출해야 함
        this.post = post;
    }

    //** business logic **//
    public void updateWith(CommentUpdateDTO dto) {
        this.content = dto.getContent();
    }
}
