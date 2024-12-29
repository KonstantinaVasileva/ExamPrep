package com.philately.service.impl;

import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Stamp;
import com.philately.repository.StampRepository;
import com.philately.service.StampService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StampServiceImpl implements StampService {

    private final StampRepository stampRepository;
    private final ModelMapper modelMapper;

    public StampServiceImpl(StampRepository stampRepository, ModelMapper modelMapper) {
        this.stampRepository = stampRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addStamp(StampDTO stampDTO) {
        Stamp stamp = modelMapper.map(stampDTO, Stamp.class);
        stampRepository.save(stamp);
    }
}
