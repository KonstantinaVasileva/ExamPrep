package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.LoginUserDTO;
import com.paintingscollectors.model.dto.RegisterUserDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;

import java.util.Set;

public interface UserService {
    boolean userRegister(RegisterUserDTO registerUserDTO);

    void register(RegisterUserDTO registerUserDTO);

    boolean login(LoginUserDTO loginUserDTO);

    User findUserByUsername(String username);

    Set<Painting> myFavoritePainting(String username);
}
