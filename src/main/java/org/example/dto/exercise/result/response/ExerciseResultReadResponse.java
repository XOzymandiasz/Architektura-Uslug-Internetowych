package org.example.dto.exercise.result.response;


import java.util.UUID;

public record ExerciseResultReadResponse(
        UUID id,
        Integer set,
        Integer reps,
        Integer weight,
        boolean personalBest
) {
}
