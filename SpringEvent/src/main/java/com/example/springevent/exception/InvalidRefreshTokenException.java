package com.example.springevent.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidRefreshTokenException extends AuthenticationException {
    public InvalidRefreshTokenException() {
        super("Refresh token is invalid, navigate to login page");
    }
}
