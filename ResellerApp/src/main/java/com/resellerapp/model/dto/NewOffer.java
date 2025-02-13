package com.resellerapp.model.dto;

import com.resellerapp.model.ConditionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewOffer {

    @NotNull(message = "Must not be null")
    @Size(min = 2, max = 50, message = "size")
    private String description;

    @NotNull(message = "Must not be null")
    private BigDecimal price;

    @NotNull(message = "Must not be null")
    private ConditionType condition;
}
