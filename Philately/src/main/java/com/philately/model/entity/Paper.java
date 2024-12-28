package com.philately.model.entity;

import com.philately.model.enums.PaperType;
import jakarta.persistence.*;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperType name;
    @Column(nullable = false)
    private String description;

    public Paper() {
    }

    public Paper(PaperType name, String description) {
        this.name = name;
        this.description = description;
    }

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
