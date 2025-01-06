package com.plannerapp.model.entity;

import com.plannerapp.model.entity.enums.PriorityType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {
    @Column(unique = true, nullable = false)
    private PriorityType name;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "priority")
    private List<Task> tasks;

    public PriorityType getName() {
        return name;
    }

    public void setName(PriorityType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
