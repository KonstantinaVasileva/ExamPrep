package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.RegisterUserDTO;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean userRegister(RegisterUserDTO registerUserDTO) {
        User user = userRepository.findUserByEmailOrUsername(registerUserDTO.getEmail(), registerUserDTO.getUsername());
        if (user == null) {
            return false;
        }

        modelMapper.map(registerUserDTO, User.class);
        return true;
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }
}
