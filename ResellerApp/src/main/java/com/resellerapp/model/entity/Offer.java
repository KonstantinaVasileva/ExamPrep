package com.resellerapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
public class Offer {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
    @ManyToOne
    private Condition condition;

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

    public Offer setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }
}
