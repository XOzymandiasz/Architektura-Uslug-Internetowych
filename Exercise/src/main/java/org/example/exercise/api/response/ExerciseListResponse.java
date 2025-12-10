package org.example.exercise.api.response;

import java.util.UUID;

public record ExerciseListResponse(
        UUID id,
        String name,
        String muscleGroup,
        String equipment,
        Integer duration
) {
}
