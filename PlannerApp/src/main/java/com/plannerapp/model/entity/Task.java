package com.plannerapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(nullable = false)
    private String description;
    @Column
    private LocalDate dueDate;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Priority priority;
    @ManyToOne
    private User user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
