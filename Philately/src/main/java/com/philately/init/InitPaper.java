package com.philately.init;

import com.philately.model.entity.Paper;
import com.philately.model.enums.PaperType;
import com.philately.repository.PaperRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitPaper implements CommandLineRunner {

    Map<PaperType, String> paperTypeMap = Map.of(
            PaperType.WOVE_PAPER, "Has an even texture without any particular distinguishing features.",
            PaperType.LAID_PAPER , "When held up to the light, shows parallel lines of greater or less width running across the stamp.",
            PaperType.GRANITE_PAPER , "Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye."
    );


    private PaperRepository paperRepository;

    public InitPaper(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (paperRepository.count() == 0) {
            for (PaperType paperType : paperTypeMap.keySet()) {
                Paper paper = new Paper(paperType, paperTypeMap.get(paperType));
                paperRepository.save(paper);
            }
        }

    }
}
