package org.example.exercise.api.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ExerciseCreateRequest(
        @NotNull UUID id,
        @NotBlank @Size(min = 5, max = 100) String name,
        @NotBlank @Size(min = 5, max = 100) String muscleGroup,
        @NotBlank @Size(min = 5, max = 100)  String equipment,
        @Positive int duration
) {
}
