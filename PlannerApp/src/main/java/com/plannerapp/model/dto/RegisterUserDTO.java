package com.plannerapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserDTO {
    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;
    @NotBlank(message = "Email is required!")
    @Email
    private String email;
    @NotBlank(message = "Password is required!")
    @Size(min = 3, max = 20,message = "Password length must be between 3 and 20 characters!")
    private String password;
    @NotBlank(message = "Must confirm password!")
    @Size(min = 3, max = 20, message = "")
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
