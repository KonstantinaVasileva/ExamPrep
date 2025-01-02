package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.RegisterUserDTO;
import com.paintingscollectors.model.entity.User;

public interface UserService {
    boolean userRegister(RegisterUserDTO registerUserDTO);

    void register(User user);

}
