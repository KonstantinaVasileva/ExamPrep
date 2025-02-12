package com.resellerapp.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @NotEmpty(message = "Username must not be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    private String username;
    @NotEmpty(message = "Password must not be null")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols")
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
