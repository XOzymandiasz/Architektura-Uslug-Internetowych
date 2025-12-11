package org.example.exercise_result.application.event;

import java.util.UUID;

public record ExerciseEvent(
        UUID id,
        String name
) {}
