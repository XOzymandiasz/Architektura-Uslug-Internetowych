package org.example.repository;

import org.example.model.ExerciseResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseResultsRepository extends JpaRepository<ExerciseResults, Integer> {
}
