package org.example.dto.exercise.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ExerciseReadDTO(
        UUID id,
        String name,
        String muscleGroup,
        String equipment,
        int duration
) {
}
