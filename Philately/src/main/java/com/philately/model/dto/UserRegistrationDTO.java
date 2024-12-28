package com.philately.model.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    private long id;
    @Size(min = 3, max = 20, message = "{register.user.username}")
    private String username;
    @Size(min = 3, max = 20, message = "{register.user.password}")
    private String password;
    @Size(min = 3, max = 20, message = "{register.user.password}")
    private String confirmPassword;
    @Email(message = "{register.user.email}")
    private String email;
    private boolean passwordEquals;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @AssertTrue(message = "Passwords should match")
    public boolean isPasswordsEqual() {
        return password.equals(confirmPassword);
    }
}
