//package org.example.infrastructure.bootstrap;
//
//import lombok.RequiredArgsConstructor;
//import org.example.web.request.ExerciseCreateRequest;
//import org.example.application.service.ExerciseService;
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
//    private final ExerciseService exerciseService;
//
//    @Override
//    public void run(String... args) {
//            UUID benchId = UUID.randomUUID();
//            exerciseService.create(new ExerciseCreateRequest(
//                    benchId, "Bench Press", "Chest", "Barbell", 40
//            ));
//
//            UUID jmId = UUID.randomUUID();
//            exerciseService.create(new ExerciseCreateRequest(
//                    jmId, "JM Press", "Chest", "Barbell", 20
//            ));
//        }
//}
