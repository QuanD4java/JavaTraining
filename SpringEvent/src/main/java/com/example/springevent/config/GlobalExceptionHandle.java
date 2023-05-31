package com.example.springevent.config;

import com.example.springevent.exception.ExpiredTokenException;
import com.example.springevent.exception.InvalidRefreshTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<String> invalidRefreshTokenException(InvalidRefreshTokenException err) {
        return ResponseEntity.status(401).body(err.getMessage());
    }
}
