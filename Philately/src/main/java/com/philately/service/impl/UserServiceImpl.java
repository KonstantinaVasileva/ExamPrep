package com.philately.service.impl;

import com.philately.model.dto.LoginUserDTO;
import com.philately.model.dto.UserRegistrationDTO;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import com.philately.service.CurrentUser;
import com.philately.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void userRegistration(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean userLogin(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByUsername(loginUserDTO.getUsername()).orElse(null);
        if (user == null ||
                loginUserDTO.getPassword() == null ||
                user.getPassword() == null) {
            return false;
        }

        boolean equals = user.getPassword().equals(passwordEncoder.encode(loginUserDTO.getPassword()));

        if (equals) {
            currentUser.setUsername(user.getUsername());
            currentUser.setLoggedIn(true);
//            return true;
        } else {

            currentUser.logout();
        }
        return false;
    }


}
