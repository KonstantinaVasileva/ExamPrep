package com.paintingscollectors.model.dto;

import com.paintingscollectors.model.entity.enums.StyleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PaintingDTO {
    @NotBlank
    @Size(min = 5, max = 40)
    private String name;
    @NotBlank
    @Size(min = 5, max = 30)
    private String author;
    @NotBlank
    @Size(max = 150)
    private String imageUrl;
    private StyleType style;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public StyleType getStyle() {
        return style;
    }

    public void setStyle(StyleType style) {
        this.style = style;
    }
}
