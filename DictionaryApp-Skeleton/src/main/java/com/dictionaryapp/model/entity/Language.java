package com.dictionaryapp.model.entity;

import com.dictionaryapp.model.enums.LanguageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {
    @Column(nullable = false, unique = true)
    private LanguageType name;
    @Column(nullable = false)
    private String description;

    public LanguageType getName() {
        return name;
    }

    public void setName(LanguageType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
