package com.plannerapp.service;

import com.plannerapp.model.dto.LoginUserDTO;
import com.plannerapp.model.dto.RegisterUserDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(RegisterUserDTO registerUserDTO) {

        return userRepository.findByUsernameOrEmail(
                registerUserDTO.getUsername(), registerUserDTO.getEmail()).isEmpty();
    }

    public void registration(RegisterUserDTO registerUserDTO) {
        User user = new User();
        user.setUsername(registerUserDTO.getUsername());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        userRepository.save(user);
    }

    public boolean login(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByUsername(loginUserDTO.getUsername()).orElse(null);
        return user != null && passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword());
    }

    public long getUserId(LoginUserDTO loginUserDTO) {
        User user = userRepository.findByUsername(loginUserDTO.getUsername()).orElse(null);
        return user != null ? user.getId() : 0;
    }
}
