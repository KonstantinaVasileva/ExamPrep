package com.resellerapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterRequest {
    @NotEmpty(message = "Username must not be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols")
    private String username;
    @Email
    private String email;
    @NotEmpty(message = "Password must not be null")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 symbols")
    private String password;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public RegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterRequest setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
