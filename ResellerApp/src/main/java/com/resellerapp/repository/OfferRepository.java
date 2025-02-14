package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    List<Offer> findByOwnerUser_Id(UUID ownerUserId);
    List<Offer> findByOwnerUserIdIsNot(UUID ownerUserId);

    List<Offer> findByBuyerUser_Id(UUID buyerUserId);

    Optional<Offer> getOfferById(UUID id);
}
