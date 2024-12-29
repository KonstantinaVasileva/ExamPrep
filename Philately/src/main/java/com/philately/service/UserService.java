package com.philately.service;

import com.philately.model.dto.LoginUserDTO;
import com.philately.model.dto.UserRegistrationDTO;

public interface UserService {

    boolean userRegistration(UserRegistrationDTO userRegistrationDTO);

    boolean userLogin(LoginUserDTO loginUserDTO);

    void userLogout();

}
