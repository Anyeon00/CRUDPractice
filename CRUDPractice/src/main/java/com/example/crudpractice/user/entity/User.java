package com.example.crudpractice.user.entity;

import com.example.crudpractice.post.entity.Post;
import com.example.crudpractice.user.service.dto.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue @Column(name = "user_id")
    private Long id;
    private String name;
    private String loginId;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder
    private User(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
    //** 연관관계 편의 메서드 **//
    public void addPost(Post post) {
        posts.add(post);
    }

    //** business logic **//
    public void updateWith(UserUpdateDTO dto) {
        this.name = dto.getName();
        this.loginId = dto.getLoginId();
        this.password = dto.getPassword();
    }

}
