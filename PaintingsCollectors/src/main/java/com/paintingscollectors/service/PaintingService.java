package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.PaintingDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.User;

import java.util.List;

public interface PaintingService {

    void addPainting(PaintingDTO paintingDTO);

    List<Painting> getMyPainting(User user);

    List<Painting> getOtherUserPainting(User user);

    List<Painting> getMostRatedPainting();

    void removePainting(long id);
}
