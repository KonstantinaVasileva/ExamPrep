package com.paintingscollectors.init;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.enums.StyleType;
import com.paintingscollectors.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.paintingscollectors.model.entity.enums.StyleType.*;

@Component
public class StyleInit implements CommandLineRunner {
    private final StyleRepository styleRepository;

    public StyleInit(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Map<StyleType, String> styles = new HashMap<>();
        styles.put(IMPRESSIONISM, "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.");
        styles.put(ABSTRACT, "Abstract art does not attempt to represent recognizable subjects in a realistic manner.");
        styles.put(EXPRESSIONISM, "Expressionism is a style of art that doesn't concern itself with realism.");
        styles.put(SURREALISM, "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.");
        styles.put(REALISM, "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance.");

        if (styleRepository.count() == 0) {
            for (StyleType styleType : styles.keySet()) {
                Style style = new Style(styleType, styles.get(styleType));
                styleRepository.save(style);

            }
        }

    }
}
