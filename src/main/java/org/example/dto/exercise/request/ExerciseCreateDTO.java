package org.example.dto.exercise.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ExerciseCreateDTO(
        @NotNull UUID id,
        @NotNull String name,
        @NotNull String muscleGroup,
        @NotNull String equipment,
        @Positive int duration
) {
}
