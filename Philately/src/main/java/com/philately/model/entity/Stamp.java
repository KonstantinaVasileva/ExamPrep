package com.philately.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "stamps")
public class Stamp extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Paper paper;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String imageURL;
    @ManyToOne
    private User owner;

    public String getName() {
        return name;
    }

    public Stamp setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Stamp setDescription(String description) {
        this.description = description;
        return this;
    }

    public Paper getPaper() {
        return paper;
    }

    public Stamp setPaper(Paper paper) {
        this.paper = paper;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Stamp setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Stamp setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Stamp setOwner(User owner) {
        this.owner = owner;
        return this;
    }
}
