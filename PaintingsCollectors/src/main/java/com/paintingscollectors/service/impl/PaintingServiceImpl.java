package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.LoggedInUser;
import com.paintingscollectors.service.PaintingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintingServiceImpl implements PaintingService {


    private final ModelMapper modelMapper;
    private final PaintingRepository paintingRepository;
    private final LoggedInUser loggedInUser;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;

    public PaintingServiceImpl(ModelMapper modelMapper, PaintingRepository paintingRepository, LoggedInUser loggedInUser, UserRepository userRepository, StyleRepository styleRepository) {
        this.modelMapper = modelMapper;
        this.paintingRepository = paintingRepository;
        this.loggedInUser = loggedInUser;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
    }

    @Override
    public void addPainting(PaintingDTO paintingDTO) {
        Painting painting = modelMapper.map(paintingDTO, Painting.class);
        User user = userRepository.findByUsername(loggedInUser.getUsername());
        painting.setOwner(user);
        Style style = styleRepository.findByName(paintingDTO.getStyle());
        painting.setStyle(style);
        paintingRepository.save(painting);
    }

    @Override
    public List<Painting> getMyPainting(User user) {
        return paintingRepository.findAllByOwner(user);
    }

    @Override
    public List<Painting> getOtherUserPainting(User owner) {
        return paintingRepository.findAllByOwnerNot(owner);
    }

    @Override
    public List<Painting> getMostRatedPainting() {
        return paintingRepository.findByOrderByVotesDesc()
                .stream()
                .limit(2)
                .toList();
    }
}
