package org.example.web.response;

import java.util.UUID;

public record ExerciseReadResponse(
        UUID id,
        String name,
        String muscleGroup,
        String equipment,
        int duration
) {
}
