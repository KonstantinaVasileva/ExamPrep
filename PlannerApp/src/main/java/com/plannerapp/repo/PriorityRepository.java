package com.plannerapp.repo;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.enums.PriorityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Priority findByName(PriorityType name);
}
