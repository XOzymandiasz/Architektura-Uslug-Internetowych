package org.example.application.service;

import org.example.web.request.ExerciseCreateRequest;
import org.example.web.request.ExerciseUpdateRequest;
import org.example.web.response.ExerciseListResponse;
import org.example.web.response.ExerciseReadResponse;
import org.example.infrastructure.mapper.ExerciseMapper;
import org.example.domain.model.Exercise;
import org.example.domain.repository.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {
    private final ExerciseRepository repository;
    private final ExerciseMapper mapper;

    public ExerciseService(ExerciseRepository repository, ExerciseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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
    }
}
