package com.resellerapp.service;

import com.resellerapp.model.dto.NewOffer;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<Offer> getMyOffers(UUID userId) {
        return offerRepository.findByOwnerUser_Id(userId);
    }

    public void removeOffers(UUID id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getOtherOffers(UUID userId) {
        return offerRepository.findByOwnerUserIdIsNot(userId);
    }

    public List<Offer> getMyBoughtOffers(UUID userId) {
        return offerRepository.findByBuyerUser_Id(userId);
    }

    public void buyOffers(UUID offerId, User user) {
        Optional<Offer> offerById = offerRepository.getOfferById(offerId);
        Offer offer = offerById.get();
        offer.setBuyerUser(user);
        offer.setOwnerUser(null);

        offerRepository.save(offer);
    }
}
