package com.resellerapp.service;

import com.resellerapp.model.dto.LoginRequest;
import com.resellerapp.model.dto.RegisterRequest;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest registerRequest) {

        Optional<User> username = userRepository.findUserByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername());
        if (username.isPresent()) {
            throw new RuntimeException("Username is already in use");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername())
        .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
        .setEmail(registerRequest.getEmail());

        userRepository.save(user);
    }

    public User loginUser(LoginRequest loginRequest) {

        Optional<User> optionalUser = userRepository.findUserByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Username or password is incorrect");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        return user;
    }

    public User getById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
