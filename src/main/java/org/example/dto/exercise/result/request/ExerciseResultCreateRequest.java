package org.example.dto.exercise.result.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ExerciseResultCreateRequest(
        @NotNull UUID id,
        @NotNull @Positive Integer set,
        @NotNull @Positive Integer reps,
        @NotNull @Positive Integer weight,
        boolean personalBest
) {
}
