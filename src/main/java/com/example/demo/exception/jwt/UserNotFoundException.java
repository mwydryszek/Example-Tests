package com.example.demo.exception.jwt;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("User with username %s not found", username));
    }
}
