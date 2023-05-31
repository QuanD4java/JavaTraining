package com.example.springevent.service;

import com.example.springevent.security.service.SecurityFilterService;
import com.example.springevent.entity.User;
import com.example.springevent.payload.LoginRequest;
import com.example.springevent.payload.LoginResponse;
import com.example.springevent.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final SecurityFilterService securityFilterService;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );
        User user = userRepository.findById(req.getUsername()).orElse(null);
        String token = securityFilterService.generateToken(user);
        String refreshToken = securityFilterService.generateRefreshToken(user);
        user.setToken(token);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        return new LoginResponse(token, refreshToken, user.getRole());
    }
}
