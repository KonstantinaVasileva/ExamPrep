package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
