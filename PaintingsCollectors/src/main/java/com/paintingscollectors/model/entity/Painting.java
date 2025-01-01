package com.paintingscollectors.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "paintings")
public class Painting extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Style style;
    @JoinColumn(nullable = false)
    @ManyToOne
    private User owner;
    @Column(nullable = false)
    private String imageUrl;
    @Column(name = "is_favorite", nullable = false)
    private boolean isFavorite;
    @Column(nullable = false)
    private int votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
