package com.example.crudpractice.post.repository;

import com.example.crudpractice.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.user join fetch p.comments where p.id = :postId")
    Optional<Post> findPostWithUserAndCommentsById(@Param("postId") Long postId);

    @Query("select p from Post p join fetch p.user")
    List<Post> findAllPostsWithUser();
}
