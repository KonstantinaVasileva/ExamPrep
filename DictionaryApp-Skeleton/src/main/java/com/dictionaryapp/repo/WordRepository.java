package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByLanguage_Name(LanguageType languageName);
}
