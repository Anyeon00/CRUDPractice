package com.example.crudpractice.post.service;

import com.example.crudpractice.post.entity.Comment;
import com.example.crudpractice.post.entity.Post;
import com.example.crudpractice.post.repository.CommentRepository;
import com.example.crudpractice.post.service.dto.CommentUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public Optional<Comment> findComment(Long id) {
        return commentRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }
    public Post saveCommentToPost(Comment comment) {
        Post post = comment.getPost();
        post.addComment(comment);
        return post;
    }
    public Post updateComment(CommentUpdateDTO dto) {
        Optional<Comment> tmp = commentRepository.findById(dto.getId());
        if (tmp.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Comment target = tmp.get();
        target.updateWith(dto);
        return target.getPost();
    }
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
