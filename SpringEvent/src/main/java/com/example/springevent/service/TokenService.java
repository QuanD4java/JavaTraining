package com.example.springevent.service;

import com.example.springevent.entity.User;
import com.example.springevent.exception.InvalidRefreshTokenException;
import com.example.springevent.security.service.SecurityFilterService;
import com.example.springevent.payload.LoginResponse;
import com.example.springevent.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {
    private final SecurityFilterService securityFilterService;
    private final UserRepository userRepository;

    public LoginResponse getNewTokens(HttpServletRequest request) {
        String refreshTokenHeader = request.getHeader("access");
        String refreshToken = refreshTokenHeader.substring(7);
        String expiredToken = request.getHeader("expiredToken");
        User user = userRepository.findById(securityFilterService.getUsername(refreshToken)).orElse(null);
        if (user.getToken().equals(expiredToken) && user.getRefreshToken().equals(refreshToken)) {
            String newToken = securityFilterService.generateToken(user);
            String newRefreshToken = securityFilterService.generateRefreshToken(user);
            user.setToken(newToken);
            user.setRefreshToken(newRefreshToken);
            userRepository.save(user);
            return new LoginResponse(newToken, newRefreshToken, user.getRole());
        }
        throw new InvalidRefreshTokenException();
    }
}
