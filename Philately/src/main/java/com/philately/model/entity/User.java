package com.philately.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    @OneToMany
    private Set<Stamp> wishedStamp;
    @OneToMany
    private Set<Stamp> purchasedStamp;

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

    public Set<Stamp> getWishedStamp() {
        return wishedStamp;
    }

    public void setWishedStamp(Set<Stamp> wishedStamp) {
        this.wishedStamp = wishedStamp;
    }

    public Set<Stamp> getPurchasedStamp() {
        return purchasedStamp;
    }

    public void setPurchasedStamp(Set<Stamp> purchasedStamp) {
        this.purchasedStamp = purchasedStamp;
    }
}
