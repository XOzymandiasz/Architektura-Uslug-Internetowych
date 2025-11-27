package org.example.exercise_result.api.event;

import lombok.RequiredArgsConstructor;
import org.example.exercise_result.application.event.ExerciseEvent;
import org.example.exercise_result.domain.model.ExerciseRecord;
import org.example.exercise_result.domain.repository.ExerciseRecordRepository;
import org.example.exercise_result.domain.repository.ExerciseResultsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/exercises")
@RequiredArgsConstructor
public class ExerciseEventController {
    private final ExerciseRecordRepository recordRepository;
    private final ExerciseResultsRepository resultsRepository;

    @PostMapping
    public void handleExerciseCreated(@RequestBody ExerciseEvent event) {
        ExerciseRecord record = new ExerciseRecord();
        record.setId(event.id());
        record.setName(event.name());
        recordRepository.save(record);
    }

    @DeleteMapping("/{id}")
    public void handleExerciseDeleted(@PathVariable UUID id) {
        resultsRepository.deleteByExerciseId(id);
        recordRepository.deleteById(id);
    }
}
