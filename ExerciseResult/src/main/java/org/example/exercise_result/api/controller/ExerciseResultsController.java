package org.example.exercise_result.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exercise_result.api.request.ExerciseResultCreateRequest;
import org.example.exercise_result.api.request.ExerciseResultUpdateRequest;
import org.example.exercise_result.api.response.ExerciseResultListResponse;
import org.example.exercise_result.api.response.ExerciseResultReadResponse;
import org.example.exercise_result.application.service.ExerciseResultsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exercise/{exerciseId}/result")
public class ExerciseResultsController {
    private final ExerciseResultsService service;

    @GetMapping("/{id}")
    public ExerciseResultReadResponse getOne(@PathVariable UUID exerciseId,
                                             @PathVariable UUID id) {
        return service.getOne(exerciseId, id);
    }

    @GetMapping
    public List<ExerciseResultListResponse> list(@PathVariable UUID exerciseId) {
        return service.getALl(exerciseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResultReadResponse create(@PathVariable UUID exerciseId,
                                             @Valid @RequestBody ExerciseResultCreateRequest createRequest) {
        return service.create(exerciseId, createRequest);
    }

    @PatchMapping("/{id}")
    public ExerciseResultReadResponse update(@PathVariable UUID exerciseId,
                                             @PathVariable UUID id,
                                             @Valid @RequestBody ExerciseResultUpdateRequest updateRequest) {
        return service.update(exerciseId, id, updateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID exerciseId,
                       @PathVariable UUID id) {
        service.delete(exerciseId, id);
    }
}
