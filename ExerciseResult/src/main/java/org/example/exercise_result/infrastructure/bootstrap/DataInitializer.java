//package org.example.infrastructure.bootstrap;
//
//import lombok.RequiredArgsConstructor;
//import org.example.dto.exercise.result.request.ExerciseResultCreateRequest;
//import org.example.service.ExerciseResultsService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@Order(1)
//@Profile("dev")
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//    private final ExerciseResultsService resultService;
//
//    @Override
//    public void run(String... args) {
//        resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 1, 6, 90, false));
//        resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 2, 6, 90, false));
//        resultService.create(benchId, new ExerciseResultCreateRequest(UUID.randomUUID(), 3, 5, 90, false));
//
//        resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 1, 8, 70, true));
//        resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 2, 8, 75, true));
//        resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 3, 5, 80, false));
//        resultService.create(jmId, new ExerciseResultCreateRequest(UUID.randomUUID(), 4, 3, 80, false));
//    }
//}
