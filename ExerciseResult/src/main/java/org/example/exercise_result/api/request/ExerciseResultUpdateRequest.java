package org.example.exercise_result.api.request;

import jakarta.validation.constraints.Positive;

public record ExerciseResultUpdateRequest(
        @Positive Integer set,
        @Positive Integer reps,
        @Positive Integer weight,
        boolean personalBest
) {
}
