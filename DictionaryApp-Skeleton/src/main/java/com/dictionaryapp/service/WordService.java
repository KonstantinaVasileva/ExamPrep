package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.model.enums.LanguageType;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public WordService(WordRepository wordRepository, LanguageRepository languageRepository, UserRepository userRepository, CurrentUser currentUser) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void addWord(WordDTO wordDTO) {
        Word word = new Word();
        word.setTerm(wordDTO.getTerm());
        word.setTranslation(wordDTO.getTranslation());
        word.setExample(wordDTO.getExample());
        word.setInputDate(wordDTO.getInputDate());
        Language language = languageRepository.findByName(wordDTO.getLanguage());
        word.setLanguage(language);
        User user = userRepository.findById(currentUser.getId()).get();
        word.setAddedBy(user);
        wordRepository.save(word);
    }

    public List<Word> getGermanyWord(LanguageType language) {
        return wordRepository.findByLanguage_Name(language);
    }
}
