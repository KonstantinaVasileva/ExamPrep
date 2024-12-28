package com.philately.model.dto;

import jakarta.validation.constraints.Size;

public class LoginUserDTO {

    @Size(min = 3, max = 20, message = "{register.user.username}")
    private String username;
    @Size(min = 3, max = 20, message = "{register.user.password}")
    private String password;

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
}
