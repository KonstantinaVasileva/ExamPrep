package com.paintingscollectors.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserDTO {
    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    private String username;
    @NotBlank(message = "Email can not be empty!")
    @Email(message = "Invalid email!")
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;
    @NotBlank(message = "Password must not be blank")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
