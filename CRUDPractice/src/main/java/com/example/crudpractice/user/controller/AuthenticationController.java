package com.example.crudpractice.user.controller;

import com.example.crudpractice.user.controller.dto.LogInDTO;
import com.example.crudpractice.user.entity.User;
import com.example.crudpractice.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> logIn(HttpServletRequest request, LogInDTO dto) {
        System.out.println(dto.getLoginId());
        System.out.println(dto.getPassword());
        User user = userService.logInValid(dto.getLoginId(), dto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("loginId", user);
        System.out.println("success");
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(null);
    }
}
