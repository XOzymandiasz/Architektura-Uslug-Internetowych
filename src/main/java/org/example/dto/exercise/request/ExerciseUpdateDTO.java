package org.example.dto.exercise.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ExerciseUpdateDTO(
        @Size(min = 5, max = 100) String name,
        @Size(min = 5, max = 100) String muscleGroup,
        @Size(min = 5, max = 100) String equipment,
        @Positive int duration
) {
}
