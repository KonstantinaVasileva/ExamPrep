package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.enums.LanguageType;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.dictionaryapp.model.enums.LanguageType.*;

@Component
public class InitLanguage implements CommandLineRunner {

    private final LanguageRepository languageRepository;
    Map<LanguageType, String> languages = Map.of(
            GERMAN, "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.",
            FRENCH, "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.",
            ITALIAN, "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.",
            SPANISH, "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure."
    );

    public InitLanguage(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (languageRepository.count() == 0) {
            for (LanguageType languageType : languages.keySet()) {
                Language language = new Language();
                language.setName(languageType);
                language.setDescription(languages.get(languageType));
                languageRepository.save(language);
            }
        }

    }
}
