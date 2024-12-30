package com.philately.service.impl;

import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Paper;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.PaperRepository;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.service.CurrentUser;
import com.philately.service.StampService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StampServiceImpl implements StampService {

    private final StampRepository stampRepository;
    private final ModelMapper modelMapper;
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public StampServiceImpl(StampRepository stampRepository, ModelMapper modelMapper, PaperRepository paperRepository, UserRepository userRepository, CurrentUser currentUser) {
        this.stampRepository = stampRepository;
        this.modelMapper = modelMapper;
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void addStamp(StampDTO stampDTO) {
        Stamp stamp = modelMapper.map(stampDTO, Stamp.class);
        Paper paper = paperRepository.findByName(stampDTO.getPaper());
        stamp.setPaper(paper);
        User user = userRepository.findById(currentUser.getId()).get();
        stamp.setOwner(user);
        stampRepository.save(stamp);
    }
}
