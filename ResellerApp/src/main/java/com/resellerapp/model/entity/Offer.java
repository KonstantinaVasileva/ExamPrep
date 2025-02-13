package com.resellerapp.model.entity;

import com.resellerapp.model.ConditionType;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
@Table(name = "Offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    private ConditionType conditionType;

    @ManyToOne
    private User ownerUser;

    @ManyToOne
    private User buyerUser;

    public Offer setId(UUID id) {
        this.id = id;
        return this;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Offer setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
        return this;
    }

    public Offer setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
        return this;
    }

    public Offer setBuyerUser(User buyerUser) {
        this.buyerUser = buyerUser;
        return this;
    }
}
