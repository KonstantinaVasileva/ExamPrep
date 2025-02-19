package com.philately.model.dto;

import com.philately.model.entity.Paper;
import com.philately.model.enums.PaperType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class StampDTO {
    @NotBlank
    @Size(min = 5, max = 20, message = "Name length must be between 5 and 20 characters!")
    private String name;
    @NotBlank
    @Size(min = 5, max = 25)
    private String description;
    @NotBlank
    @Size(max = 150)
    private String imageURL;
    @Positive
    private BigDecimal price;
    private PaperType paper;

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

    public PaperType getPaper() {
        return paper;
    }

    public void setPaper(PaperType paper) {
        this.paper = paper;
    }
}
