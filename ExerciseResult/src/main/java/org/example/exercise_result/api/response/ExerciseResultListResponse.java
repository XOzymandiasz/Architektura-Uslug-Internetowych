package org.example.exercise_result.api.response;

import java.util.UUID;

public record ExerciseResultListResponse(
        UUID id,
        Integer set,
        Integer reps,
        Integer weight,
        boolean personalBest
) {
}
