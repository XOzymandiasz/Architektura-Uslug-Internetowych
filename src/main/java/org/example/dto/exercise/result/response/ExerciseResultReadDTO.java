package org.example.dto.exercise.result.response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ExerciseResultReadDTO(
        @NotNull UUID id,
        @NotNull Integer set,
        @NotNull Integer reps,
        @NotNull Integer weight,
        boolean personalBest
) {
}
