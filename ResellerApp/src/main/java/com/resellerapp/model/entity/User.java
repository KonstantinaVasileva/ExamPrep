package com.resellerapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
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

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public User setOffers(List<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public List<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public User setBoughtOffers(List<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
