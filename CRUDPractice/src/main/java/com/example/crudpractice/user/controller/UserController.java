package com.example.crudpractice.user.controller;

import com.example.crudpractice.user.entity.User;
import com.example.crudpractice.user.service.UserService;
import com.example.crudpractice.user.service.dto.UserUpdateDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //create
    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestParam String name,
                                      @RequestParam String loginId,
                                      @RequestParam String password) {
        userService.saveUser(User.builder()
                                        .name(name)
                                        .loginId(loginId)
                                        .password(password)
                                        .build());
        return ResponseEntity.ok(null);
    }
    //read _단건조회
    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.findUser(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return ResponseEntity.ok(user.get());
    }
    //read _전체조회
    @GetMapping()
    public ResponseEntity<?> findAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    //update
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestParam Long id,
                                        @RequestParam String name,
                                        @RequestParam String loginId,
                                        @RequestParam String password,
                                        HttpServletRequest request) {
        userService.validAuthorization(request);
        userService.updateUser(UserUpdateDTO.builder()
                                        .id(id)
                                        .name(name)
                                        .loginId(loginId)
                                        .password(password)
                                        .build());
        return ResponseEntity.ok(null);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id, HttpServletRequest request) {
        User user = userService.validAuthorization(request);
        userService.deleteUser(user);
        return ResponseEntity.ok(null);
    }
}
