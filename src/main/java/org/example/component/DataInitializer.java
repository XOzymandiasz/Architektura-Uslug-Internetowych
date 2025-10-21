package org.example.component;

import org.example.model.Exercise;
import org.example.model.ExerciseResults;
import org.example.service.ExerciseResultsService;
import org.example.service.ExerciseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {
    private final ExerciseService exerciseService;

    public DataInitializer(ExerciseService exerciseService, ExerciseResultsService resultsService) {
        this.exerciseService = exerciseService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!exerciseService.getALl().isEmpty()) return;

        Exercise benchPress = Exercise.builder()
                .id(UUID.randomUUID())
                .name("Bench_Press")
                .muscle_group("Chest")
                .equipment("Barbell")
                .duration(40)
                .build();

        benchPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(1)
                .reps(6)
                .weight(90)
                .personal_best(false)
                .build());

        benchPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(2)
                .reps(6)
                .weight(90)
                .personal_best(false)
                .build());

        benchPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(3)
                .reps(5)
                .weight(90)
                .personal_best(false)
                .build());

        Exercise JMPress = Exercise.builder()
                .id(UUID.randomUUID())
                .name("JM_Press")
                .muscle_group("Chest")
                .equipment("Barbell")
                .duration(20)
                .build();

        JMPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(1)
                .reps(8)
                .weight(70)
                .personal_best(true)
                .build());

        JMPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(2)
                .reps(8)
                .weight(75)
                .personal_best(true)
                .build());

        JMPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(3)
                .reps(5)
                .weight(80)
                .personal_best(false)
                .build());

        JMPress.addResult(ExerciseResults.builder()
                .id(UUID.randomUUID())
                .set(4)
                .reps(3)
                .weight(80)
                .personal_best(false)
                .build());

        exerciseService.save(benchPress);
        exerciseService.save(JMPress);
    }
}
