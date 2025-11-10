package org.example.dto.exercise.result.request;

import jakarta.validation.constraints.Positive;

public record ExerciseResultUpdateDTO(
        @Positive Integer set,
        @Positive Integer reps,
        @Positive Integer weight,
        boolean personalBest
) {
}
