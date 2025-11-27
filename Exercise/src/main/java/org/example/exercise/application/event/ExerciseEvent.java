package org.example.exercise.application.event;

import java.util.UUID;

public record ExerciseEvent(
        UUID id,
        String name
) {}
