package com.example.crudpractice.post.entity;

import com.example.crudpractice.post.service.dto.PostUpdateDTO;
import com.example.crudpractice.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    @Builder
    private Post(String title, String content,User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        user.addPost(this);
    }

    //** 연관관계 편의 메서드 **//
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.addPost(this);
    }

    //** business logic **//
    public void updateWith(PostUpdateDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
