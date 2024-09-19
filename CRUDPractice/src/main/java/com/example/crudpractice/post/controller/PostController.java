package com.example.crudpractice.post.controller;

import com.example.crudpractice.post.entity.Post;
import com.example.crudpractice.post.service.PostService;
import com.example.crudpractice.post.service.dto.PostUpdateDTO;
import com.example.crudpractice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    //create
    @PostMapping()
    public ResponseEntity<?> registerPost(@RequestParam String title,
                                        @RequestParam String content,
                                        @RequestParam Long userId) {
        postService.savePost(Post.builder()
                                    .title(title)
                                    .content(content)
                                    .user(userService.findUser(userId).get())
                                    .build());
        return ResponseEntity.ok(null);
    }
    //read _단건조회
    @GetMapping
    public ResponseEntity<?> findPost(Long id) {
        Optional<Post> post = postService.findPost(id);
        if (post.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return ResponseEntity.ok(post.get());
    }
    //read _전체조회
    @GetMapping("/list")
    public ResponseEntity<?> findAllPosts() {
        List<Post> allPosts = postService.findAllPosts();
        return ResponseEntity.ok(allPosts);
    }
    //update
    @PutMapping
    public ResponseEntity<?> updatePost(@RequestParam Long id,
                                        @RequestParam String title,
                                        @RequestParam String content) {
        Post post = postService.updatePost(PostUpdateDTO.builder()
                .id(id)
                .title(title)
                .content(content)
                .build());
        return ResponseEntity.ok(null);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findPost(id);
        if (post.isEmpty()) {
            throw new IllegalArgumentException();
        }
        postService.deletePost(post.get());
        return ResponseEntity.ok(null);
    }
}
