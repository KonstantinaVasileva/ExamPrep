package com.philately.service.impl;

import com.philately.model.dto.UserRegistrationDTO;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import com.philately.service.UserService;
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
    public void userRegistration(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);
        userRepository.save(user);
    }
}
