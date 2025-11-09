package org.example.dto.exercise.result.request;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ExerciseResultCreateDTO(
        @NotNull UUID id,
        @NotNull Integer set,
        @NotNull Integer reps,
        @NotNull Integer weight,
        boolean personalBest
) {
}
