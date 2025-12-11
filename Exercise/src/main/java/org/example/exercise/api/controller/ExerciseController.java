package org.example.exercise.api.controller;

import jakarta.validation.Valid;
import org.example.exercise.api.request.ExerciseCreateRequest;
import org.example.exercise.api.request.ExerciseUpdateRequest;
import org.example.exercise.api.response.ExerciseListResponse;
import org.example.exercise.api.response.ExerciseReadResponse;
import org.example.exercise.application.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {
    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ExerciseReadResponse getOne(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/list")
    public List<ExerciseListResponse> list() {
        return service.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseReadResponse create(@Valid @RequestBody ExerciseCreateRequest createRequest) {
        return service.create(createRequest);
    }

    @PatchMapping("/{id}")
    public ExerciseReadResponse update(@PathVariable UUID id,
                                       @Valid @RequestBody ExerciseUpdateRequest updateRequest) {
        return service.update(id, updateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
