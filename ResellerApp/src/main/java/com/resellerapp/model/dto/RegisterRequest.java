package com.resellerapp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @NotEmpty(message = "Username must not be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    private String username;
    @Email
    @NotEmpty(message = "Email must not be null")
    private String email;
    @NotEmpty(message = "Password must not be null")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols")
    private String password;
    @NotEmpty(message = "Password must not be null")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols")
    private String confirmPassword;

    public RegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterRequest setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
