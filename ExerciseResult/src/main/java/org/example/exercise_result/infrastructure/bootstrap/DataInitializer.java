package org.example.exercise_result.infrastructure.bootstrap;

import lombok.RequiredArgsConstructor;
import org.example.exercise_result.api.request.ExerciseResultCreateRequest;
import org.example.exercise_result.application.service.ExerciseResultsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ExerciseResultsService resultService;
    private final UUID benchId = UUID.fromString("630feb5a-37d2-47e1-9e75-0cae5be13180");
    private final UUID JMId = UUID.fromString("4dca6bc6-6001-4408-9d04-50a640a8ebff");


    @Override
    public void run(String... args) {
        resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 1, 6, 90, false));
        resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 2, 6, 90, false));
        resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 3, 5, 90, false));

        resultService.create(JMId, new ExerciseResultCreateRequest(UUID.randomUUID(), 1, 8, 70, true));
        resultService.create(JMId, new ExerciseResultCreateRequest(UUID.randomUUID(), 2, 8, 75, true));
        resultService.create(JMId, new ExerciseResultCreateRequest(UUID.randomUUID(), 3, 5, 80, false));
        resultService.create(JMId, new ExerciseResultCreateRequest(UUID.randomUUID(), 4, 3, 80, false));
    }
}
