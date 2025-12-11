package org.example.exercise_result.api.response;


import java.util.UUID;

public record ExerciseResultReadResponse(
        UUID id,
        Integer set,
        Integer reps,
        Integer weight,
        boolean personalBest
) {
}
