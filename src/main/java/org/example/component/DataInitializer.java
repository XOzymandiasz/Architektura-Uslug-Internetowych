package org.example.component;

import lombok.RequiredArgsConstructor;
import org.example.dto.exercise.request.ExerciseCreateRequest;
import org.example.dto.exercise.result.request.ExerciseResultCreateRequest;
import org.example.service.ExerciseResultsService;
import org.example.service.ExerciseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ExerciseService exerciseService;
    private final ExerciseResultsService resultService;

    @Override
    public void run(String... args) {
            UUID benchId = UUID.randomUUID();
            exerciseService.create(new ExerciseCreateRequest(
                    benchId, "Bench Press", "Chest", "Barbell", 40
            ));

            resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 1, 6, 90, false));
            resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 2, 6, 90, false));
            resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 3, 5, 90, false));

            UUID jmId = UUID.randomUUID();
            exerciseService.create(new ExerciseCreateRequest(
                    jmId, "JM Press", "Chest", "Barbell", 20
            ));

            resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 1, 8, 70, true));
            resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 2, 8, 75, true));
            resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 3, 5, 80, false));
            resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 4, 3, 80, false));
        }
}
