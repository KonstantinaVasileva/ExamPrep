package com.philately.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "stamps")
public class Stamp extends BaseEntity {

    private String name;
    private String description;
    @ManyToOne
    private Paper paper;
    private BigDecimal price;
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
