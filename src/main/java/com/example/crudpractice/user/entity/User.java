package com.example.crudpractice.user.entity;

import com.example.crudpractice.board.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue @Column(name = "user_id")
    private Long id;
    private String name;
    private String loginId;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> comments = new ArrayList<>();

    @Builder
    private User(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}
