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
            //UUID benchId = UUID.fromString("630feb5a-37d2-47e1-9e75-0cae5be13180");
            //exerciseService.create(new ExerciseCreateRequest(
            //        benchId, "Bench Press", "Chest", "Barbell", 40
            //));
//
            //UUID jmId = UUID.fromString("4dca6bc6-6001-4408-9d04-50a640a8ebff");
            //exerciseService.create(new ExerciseCreateRequest(
            //        jmId, "JM Press", "Chest", "Barbell", 20
            //));
        }
}
