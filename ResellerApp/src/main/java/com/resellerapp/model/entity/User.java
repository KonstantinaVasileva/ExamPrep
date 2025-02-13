package com.resellerapp.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique=true)
    private String email;

    @OneToMany
    private List<Offer> offers;

    @OneToMany
    private List<Offer> boughtOffers;

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public User setBoughtOffers(List<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
