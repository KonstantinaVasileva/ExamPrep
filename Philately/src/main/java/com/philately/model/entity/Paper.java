package com.philately.model.entity;

import com.philately.model.enums.PaperType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity {
    @Column(unique = true, nullable = false)
    private PaperType name;
    @Column(nullable = false)
    private String description;

    public PaperType getName() {
        return name;
    }

    public void setName(PaperType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
