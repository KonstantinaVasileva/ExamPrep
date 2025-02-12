package com.resellerapp.model.entity;

import com.resellerapp.model.ConditionName;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ConditionName name;
    @Column(nullable = false)
    private String description;

    public UUID getId() {
        return id;
    }

    public Condition setId(UUID id) {
        this.id = id;
        return this;
    }

    public ConditionName getName() {
        return name;
    }

    public Condition setName(ConditionName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Condition setDescription(String description) {
        this.description = description;
        return this;
    }
}
