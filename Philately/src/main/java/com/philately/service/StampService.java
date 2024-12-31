package com.philately.service;

import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;

import java.util.List;
import java.util.Set;

public interface StampService {
    void addStamp(StampDTO stampDTO);

    Set<Stamp> getAddedStamp(User user);

    List<Stamp> getOthersStamp(User user);

    void addToWishlist(long stampId, long userId);

    void removeFromWishlist(long stampId, long userId);

    void buyAllStamps(long id);
}
