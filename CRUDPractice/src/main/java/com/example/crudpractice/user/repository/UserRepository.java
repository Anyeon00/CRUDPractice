package com.example.crudpractice.user.repository;

import com.example.crudpractice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    @Query("select u from User u join fetch u.posts where u.id = :userId")
    Optional<User> findUserWithPostsById(@Param("userId") Long userId);

    @Query("select u from User u join fetch u.posts")
    List<User> findAllUsersWithPosts();

}
