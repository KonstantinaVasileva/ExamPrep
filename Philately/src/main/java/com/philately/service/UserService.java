package com.philately.service;

import com.philately.model.dto.LoginUserDTO;
import com.philately.model.dto.UserRegistrationDTO;

public interface UserService {

    void userRegistration(UserRegistrationDTO userRegistrationDTO);

    boolean userLogin(LoginUserDTO loginUserDTO);
}
