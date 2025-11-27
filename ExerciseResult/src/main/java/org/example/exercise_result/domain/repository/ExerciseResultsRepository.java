package org.example.exercise_result.domain.repository;

import org.example.exercise_result.domain.model.ExerciseResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.UUID;

public interface ExerciseResultsRepository extends JpaRepository<ExerciseResults, UUID> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    List<ExerciseResults> findByExerciseIdOrderBySetAscRepsAscWeightAsc(UUID exerciseId);
    java.util.Optional<ExerciseResults> findByIdAndExerciseId(UUID id, UUID exerciseId);
    boolean existsByExerciseIdAndSetAndRepsAndWeight(UUID exerciseId, Integer set, Integer reps, Integer weight);
}
