package com.plannerapp.init;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.enums.PriorityType;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.plannerapp.model.entity.enums.PriorityType.*;

@Component
public class InitPriority implements CommandLineRunner {

    private final PriorityRepository priorityRepository;
    Map<PriorityType, String> priorities = Map.of(
            URGENT, "An urgent problem that blocks the system use until the issue is resolved.",
            IMPORTANT, "A core functionality that your product is explicitly supposed to perform is compromised.",
            LOW, "Should be fixed if time permits but can be postponed."
    );

    public InitPriority(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (priorityRepository.count() == 0) {
            for (PriorityType priorityType : priorities.keySet()) {
                Priority priority = new Priority();
                priority.setName(priorityType);
                priority.setDescription(priorities.get(priorityType));
                priorityRepository.save(priority);
            }
        }

    }
}
