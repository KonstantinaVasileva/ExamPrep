package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.entity.enums.StyleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{
    @Column(nullable = false, unique = true)
    private StyleType name;
    @Column(nullable = false)
    private String description;

    public Style() {
    }

    public Style(StyleType name, String description) {
        this.name = name;
        this.description = description;
    }

    public StyleType getName() {
        return name;
    }

    public void setName(StyleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
