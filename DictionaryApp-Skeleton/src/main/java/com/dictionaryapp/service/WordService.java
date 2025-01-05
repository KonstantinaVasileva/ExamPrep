package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;

    public WordService(WordRepository wordRepository, LanguageRepository languageRepository) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
    }

    public void addWord(WordDTO wordDTO) {
        Word word = new Word();
        word.setTerm(wordDTO.getTerm());
        word.setTranslation(wordDTO.getTranslation());
        word.setExample(wordDTO.getExample());
        word.setInputDate(wordDTO.getInputDate());
        Language language = languageRepository.findByName(wordDTO.getLanguage());
        word.setLanguage(language);
        wordRepository.save(word);
    }
}
