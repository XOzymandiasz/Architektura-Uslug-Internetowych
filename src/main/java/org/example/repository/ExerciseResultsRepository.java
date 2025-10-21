package org.example.repository;

import org.example.model.ExerciseResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ExerciseResultsRepository extends JpaRepository<ExerciseResults, UUID> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ExerciseResults r where r.id = :id")
    void hardDeleteById(@Param("id") UUID id);
}
