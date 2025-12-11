package org.example.exercise_result.domain.repository;

import org.example.exercise_result.domain.model.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecord, UUID> {
}
