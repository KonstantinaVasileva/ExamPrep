package com.dictionaryapp.model.dto;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class WordDTO {
    @NotBlank
    @Size(min = 2, max = 40, message = "The term length must be between 2 and 40 characters!")
    private String term;
    @NotBlank
    @Size(min = 2, max = 80, message = "The translation length must be between 2 and 80 characters!")
    private String translation;
    @Size(min = 2, max = 200, message = "The term length must be between 2 and 200 characters!")
    private String example;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The input date must be in the past or present!")
    private LocalDate inputDate;
    @NotNull(message = "You must select language!")
    private LanguageType language;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }



    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }
}
