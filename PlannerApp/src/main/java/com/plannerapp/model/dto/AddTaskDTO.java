package com.plannerapp.model.dto;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.enums.PriorityType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddTaskDTO {
    @NotBlank(message = "Description is required!")
    @Size(min = 2, max = 50, message = "Description length must be between 3 and 20 characters!")
    private String description;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    @NotNull(message = "Yo must select priority!")
    private PriorityType priority;

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

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }
}
