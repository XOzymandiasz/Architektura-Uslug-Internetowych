package org.example.repository;

import org.example.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
    public List<Exercise> findByName(String name);

    boolean existsByNameIgnoreCase(String name);
}
