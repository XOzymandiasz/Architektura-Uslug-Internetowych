package org.example.exercise.infrastructure.bootstrap;

import lombok.RequiredArgsConstructor;
import org.example.exercise.api.request.ExerciseCreateRequest;
import org.example.exercise.application.service.ExerciseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ExerciseService exerciseService;

    @Override
    public void run(String... args) {
            UUID benchId = UUID.randomUUID();
            exerciseService.create(new ExerciseCreateRequest(
                    benchId, "Bench Press", "Chest", "Barbell", 40
            ));

            UUID jmId = UUID.randomUUID();
            exerciseService.create(new ExerciseCreateRequest(
                    jmId, "JM Press", "Chest", "Barbell", 20
            ));
        }
}
