package com.philately.model.dto;

import com.philately.model.entity.Paper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class StampDTO {
    @NotBlank
    @Size(min = 5, max = 20)
    private String name;
    @NotBlank
    @Size(min = 5, max = 25)
    private String description;
    @NotBlank
    @Size(max = 150)
    private String imageURL;
    @NotBlank
    @Positive
    private BigDecimal price;
    @NotBlank
    private Paper paper;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
