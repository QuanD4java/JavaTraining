package com.example.springevent.payload;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String refreshToken;
    private String type = "Bearer ";
    private String role;

    public LoginResponse(String token, String refreshToken, String role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.role = role;
    }

}
