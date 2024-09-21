package com.example.crudpractice.post.service;

import com.example.crudpractice.post.entity.Comment;
import com.example.crudpractice.post.entity.Post;
import com.example.crudpractice.post.repository.CommentRepository;
import com.example.crudpractice.post.repository.PostRepository;
import com.example.crudpractice.post.service.dto.PostUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Optional<Post> findPost(Long id) {
        return postRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
    public Post updatePost(PostUpdateDTO dto) {
        Optional<Post> tmp = postRepository.findById(dto.getId());
        if (tmp.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Post target = tmp.get();
        target.updateWith(dto);
        return target;
    }
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllPostsWithUserForPage(pageable);
    }
}
