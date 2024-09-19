package com.example.crudpractice.post.controller;

import com.example.crudpractice.post.entity.Comment;
import com.example.crudpractice.post.entity.Post;
import com.example.crudpractice.post.service.CommentService;
import com.example.crudpractice.post.service.dto.CommentUpdateDTO;
import com.example.crudpractice.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/comment")
public class CommentController {
    private final UserService userService;
    private final CommentService commentService;
    //comment 등록
    @PostMapping
    public ResponseEntity<?> registerCommentToPost(Comment comment, HttpServletRequest request) {
        userService.validAuthorization(request);
        commentService.saveCommentToPost(comment);
        return ResponseEntity.ok(comment.getPost());
    }

    //comment 게시글로 조회
    @GetMapping
    public ResponseEntity<?> findCommentsByPost(Post post) {
        commentService.findCommentsByPost(post);
        return ResponseEntity.ok(post);
    }

    //comment 수정
    @PutMapping
    public ResponseEntity<?> updateComment(CommentUpdateDTO dto, HttpServletRequest request) {
        userService.validAuthorization(request);
        commentService.updateComment(CommentUpdateDTO.builder()
                                                        .id(dto.getId())
                                                        .content(dto.getContent())
                                                        .build());
        Optional<Comment> comment = commentService.findComment(dto.getId());
        Post post = comment.get().getPost();
        return ResponseEntity.ok(post);
    }

    //comment 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteComment(Comment comment, HttpServletRequest request) {
        userService.validAuthorization(request);
        Post post = comment.getPost();
        commentService.deleteComment(comment);
        return ResponseEntity.ok(post);
    }
}
