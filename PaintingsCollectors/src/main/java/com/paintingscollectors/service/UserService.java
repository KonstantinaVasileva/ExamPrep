package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.LoginUserDTO;
import com.paintingscollectors.model.dto.RegisterUserDTO;

public interface UserService {
    boolean userRegister(RegisterUserDTO registerUserDTO);

    void register(RegisterUserDTO registerUserDTO);

    boolean login(LoginUserDTO loginUserDTO);
}
