package com.philately.service;

import com.philately.model.dto.LoginUserDTO;
import com.philately.model.dto.UserRegistrationDTO;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;

import java.util.Set;

public interface UserService {

    boolean userRegistration(UserRegistrationDTO userRegistrationDTO);

    boolean userLogin(LoginUserDTO loginUserDTO);

    void userLogout();

    User findById(long id);

    Set<Stamp> getPurchasesStamp(Long id);

    Set<Stamp> getMyWishlist(Long id);
}
