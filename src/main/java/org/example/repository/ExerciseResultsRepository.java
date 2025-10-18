package org.example.repository;

import org.example.models.ExerciseResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseResultsRepository extends JpaRepository<ExerciseResults, Integer> {
}
