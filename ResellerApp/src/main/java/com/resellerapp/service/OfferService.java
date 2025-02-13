package com.resellerapp.service;

import com.resellerapp.model.dto.NewOffer;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void addOffer(NewOffer newOffer, User user) {
        Offer offer = new Offer();
        offer.setDescription(newOffer.getDescription())
                .setPrice(newOffer.getPrice())
                .setConditionType(newOffer.getCondition())
                .setOwnerUser(user);

        offerRepository.save(offer);
    }
}
