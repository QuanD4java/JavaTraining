package com.example.springevent.controller;

import com.example.springevent.entity.User;
import com.example.springevent.payload.LoginRequest;
import com.example.springevent.service.AuthenticationService;
import com.example.springevent.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authenticationService.login(req));
    }

    @GetMapping
    public String get() {
        return "ok";
    }
}
