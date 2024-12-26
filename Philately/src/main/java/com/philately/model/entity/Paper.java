package com.philately.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "papers")
public class Paper extends BaseEntity {
    private String name;
    private String description;
}
