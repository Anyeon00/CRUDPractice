package com.example.crudpractice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.example.crudpractice")
public class ExceptionController {
    @ExceptionHandler
    public ResponseEntity<?> errorResponse(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
