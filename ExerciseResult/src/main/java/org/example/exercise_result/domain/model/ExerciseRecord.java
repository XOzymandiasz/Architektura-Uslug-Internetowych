package org.example.exercise_result.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "exercise_records")
@Getter
@Setter
public class ExerciseRecord {
    @Id
    private UUID id;
    private String name;
}
