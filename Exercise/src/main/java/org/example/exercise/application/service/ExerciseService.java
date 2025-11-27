package org.example.exercise.application.service;

import lombok.RequiredArgsConstructor;
import org.example.exercise.api.request.ExerciseCreateRequest;
import org.example.exercise.api.request.ExerciseUpdateRequest;
import org.example.exercise.api.response.ExerciseListResponse;
import org.example.exercise.api.response.ExerciseReadResponse;
import org.example.exercise.application.event.ExerciseEventClient;
import org.example.exercise.infrastructure.mapper.ExerciseMapper;
import org.example.exercise.domain.model.Exercise;
import org.example.exercise.domain.repository.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository repository;
    private final ExerciseMapper mapper;
    private final ExerciseEventClient eventClient;

    public List<ExerciseListResponse> list() {
        return mapper.toListDTOs(repository.findAll());
    }

    public Exercise getByName(String name) {
        return repository.findByName(name).getFirst();
    }

    public ExerciseReadResponse create(ExerciseCreateRequest exerciseCreateDTO) {
        if (repository.existsById(exerciseCreateDTO.id())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Exercise with given id already exists");
        }
        if (repository.existsByNameIgnoreCase(exerciseCreateDTO.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Exercise with given name already exists");
        }

        Exercise exercise = mapper.toEntity(exerciseCreateDTO);
        repository.save(exercise);

        eventClient.sendExerciseCreated(exercise.getId(),  exercise.getName());

        return mapper.toReadDTO(exercise);
    }

    public ExerciseReadResponse update(UUID id, ExerciseUpdateRequest exerciseUpdateDTO) {
        Exercise exercise = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise with id: " + id));

        if (exerciseUpdateDTO.name() != null
                && !exerciseUpdateDTO.name().equalsIgnoreCase(exercise.getName())
                && repository.existsByNameIgnoreCase(exerciseUpdateDTO.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Exercise with given name already exists");
        }

        mapper.update(exercise, exerciseUpdateDTO);
        repository.save(exercise);

        return mapper.toReadDTO(exercise);
    }

    public ExerciseReadResponse getById(UUID id) {
        Exercise exercise = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise with id " + id + " not found"));
        return mapper.toReadDTO(exercise);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Exercise not found");
        }
        repository.deleteById(id);
        eventClient.sendExerciseDeleted(id);
    }
}
