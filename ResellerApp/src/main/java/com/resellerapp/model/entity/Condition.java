package com.resellerapp.model.entity;

import com.resellerapp.model.ConditionName;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ConditionName name;
    @Column(nullable = false)
    private String description;

    public Condition setId(UUID id) {
        this.id = id;
        return this;
    }

    public Condition setName(ConditionName name) {
        this.name = name;
        return this;
    }

    public Condition setDescription(String description) {
        this.description = description;
        return this;
    }
}
