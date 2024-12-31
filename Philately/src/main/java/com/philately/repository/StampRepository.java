package com.philately.repository;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    Set<Stamp> findByAdded(User user);

    List<Stamp> findByAddedIsNot(User owner);
}
