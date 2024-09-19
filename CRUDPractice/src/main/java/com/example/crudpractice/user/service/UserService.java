package com.example.crudpractice.user.service;

import com.example.crudpractice.user.entity.User;
import com.example.crudpractice.user.repository.UserRepository;
import com.example.crudpractice.user.service.dto.UserUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(UserUpdateDTO dto) {
        Optional<User> tmp = userRepository.findById(dto.getId());
        if (tmp.isEmpty()) {
            throw new IllegalArgumentException();
        }
        User target = tmp.get();
        target.updateWith(dto);
        return target;
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    //** Security Logic **//
    public User logInValid(String loginId, String password){
        User user = validLoginId(loginId);
        validPassword(password, user);
        return user;
    }
    private User validLoginId(String loginId) {
        Optional<User> tmp = userRepository.findByLoginId(loginId);
        if (tmp.isEmpty()) {
            throw new IllegalArgumentException("Incorrect LoginId");
        }
        return tmp.get();
    }
    private void validPassword(String password, User user) {
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect Password");
        }
    }
    public User validAuthorization(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new RuntimeException("Not Authenticated User: redirect to login page");
        }
        User user = (User) session.getAttribute("loginId");
        if (user == null) {
            throw new RuntimeException("Not Authenticated User: redirect to login page");
        }
        return user;
    }
}
