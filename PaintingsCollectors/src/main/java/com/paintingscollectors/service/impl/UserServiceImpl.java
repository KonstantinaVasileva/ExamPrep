package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.LoginUserDTO;
import com.paintingscollectors.model.dto.RegisterUserDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean userRegister(RegisterUserDTO registerUserDTO) {
        User user = userRepository.findUserByEmailOrUsername(registerUserDTO.getEmail(), registerUserDTO.getUsername());
        return user == null;
    }

    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        User user = modelMapper.map(registerUserDTO, User.class);
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        userRepository.save(user);
    }

    @Override
    public boolean login(LoginUserDTO loginUserDTO) {

        User user = userRepository.findByUsername(loginUserDTO.getUsername());
        if (user == null || !passwordEncoder.matches(loginUserDTO.getPassword(), user.getPassword())) {
            return false;
        }

        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Set<Painting> myFavoritePainting(String username) {
        return userRepository.findByUsername(username).getFavoritePaintings();
    }


}
