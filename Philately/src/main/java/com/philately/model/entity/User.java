package com.philately.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    @OneToMany
    private Stamp wishedStamp;
    @OneToMany
    private Stamp purchasedStamp;

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

    public Stamp getWishedStamp() {
        return wishedStamp;
    }

    public User setWishedStamp(Stamp wishedStamp) {
        this.wishedStamp = wishedStamp;
        return this;
    }

    public Stamp getPurchasedStamp() {
        return purchasedStamp;
    }

    public User setPurchasedStamp(Stamp purchasedStamp) {
        this.purchasedStamp = purchasedStamp;
        return this;
    }
}
