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

import java.util.List;
import java.util.Set;

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
        stamp.setAdded(user);
//        user.getAddedStamp().add(stamp);
        stampRepository.save(stamp);
    }

    @Override
    public Set<Stamp> getAddedStamp(User user) {
        return stampRepository.findByAdded(user);
    }

    @Override
    public List<Stamp> getOthersStamp(User user) {
        return stampRepository.findByAddedIsNot(user);
    }

    @Override
    public void addToWishlist(long stampId, long userId) {
        Stamp stamp = stampRepository.findById(stampId).get();
        User user = userRepository.findById(userId).get();

        user.getWishedStamp().add(stamp);
        userRepository.save(user);
    }

    @Override
    public void removeFromWishlist(long stampId, long userId) {
//        Stamp stamp = stampRepository.findById(stampId).get();
        User user = userRepository.findById(userId).get();
        Stamp stamp = user.getWishedStamp().stream().filter(s -> s.getId() == stampId).findFirst().get();
        user.getWishedStamp().remove(stamp);
        userRepository.save(user);

    }

    @Override
    public void buyAllStamps(long userId) {
        User user = userRepository.findById(userId).get();
        Set<Stamp> stamps = user.getWishedStamp();
        user.getPurchasedStamp().addAll(stamps);
        for (Stamp stamp : stamps) {
            stamp.setAdded(null);
            stampRepository.save(stamp);
        }
        user.getWishedStamp().clear();
        userRepository.save(user);
    }
}
