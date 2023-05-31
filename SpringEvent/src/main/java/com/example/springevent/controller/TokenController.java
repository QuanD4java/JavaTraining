package com.example.springevent.controller;

import com.example.springevent.payload.LoginResponse;
import com.example.springevent.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-new-token")
@AllArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponse> newTokens(HttpServletRequest request) {
        return ResponseEntity.ok(tokenService.getNewTokens(request));
    }
}
