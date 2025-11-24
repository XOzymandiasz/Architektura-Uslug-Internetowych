package org.example.service;

import org.example.dto.exercise.result.request.ExerciseResultCreateRequest;
import org.example.dto.exercise.result.request.ExerciseResultUpdateRequest;
import org.example.dto.exercise.result.response.ExerciseResultListResponse;
import org.example.dto.exercise.result.response.ExerciseResultReadResponse;
import org.example.mapper.ExerciseResultsMapper;
import org.example.model.Exercise;
import org.example.model.ExerciseResults;
import org.example.repository.ExerciseRepository;
import org.example.repository.ExerciseResultsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseResultsService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseResultsRepository repository;
    private final ExerciseResultsMapper mapper;

    public ExerciseResultsService(ExerciseRepository exerciseRepository, ExerciseResultsRepository repository, ExerciseResultsMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ExerciseResultListResponse> getALl(UUID exerciseId) {
        ensureExerciseExists(exerciseId);
        return repository.findByExerciseIdOrderBySetAscRepsAscWeightAsc(exerciseId)
                .stream()
                .map(mapper::toListDTO)
                .toList();
    }

    public ExerciseResultReadResponse create(UUID exerciseId, ExerciseResultCreateRequest exerciseResultCreateDTO) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Exercise not found"));

        if (repository.existsById(exerciseResultCreateDTO.id())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Result with given id already exists");
        }
        if (repository.existsByExerciseIdAndSetAndRepsAndWeight(exerciseId, exerciseResultCreateDTO.set(), exerciseResultCreateDTO.reps(), exerciseResultCreateDTO.weight())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Duplicate result for this exercise");
        }

        ExerciseResults entity = mapper.toEntity(exerciseResultCreateDTO);
        entity.setExercise(exercise);
        repository.save(entity);
        return mapper.toReadDTO(entity);
    }

    public ExerciseResultReadResponse update(UUID exerciseId, UUID resultId, ExerciseResultUpdateRequest exerciseResultUpdateDTO) {
        ensureExerciseExists(exerciseId);
        ExerciseResults entity = repository.findByIdAndExerciseId(resultId, exerciseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Result not found"));

        Integer newSet = exerciseResultUpdateDTO.set() != null ? exerciseResultUpdateDTO.set() : entity.getSet();
        Integer newReps = exerciseResultUpdateDTO.reps() != null ? exerciseResultUpdateDTO.reps() : entity.getReps();
        Integer newWeight = exerciseResultUpdateDTO.weight() != null ? exerciseResultUpdateDTO.weight() : entity.getWeight();
        boolean sameTriple = newSet.equals(entity.getSet())
                && newReps.equals(entity.getReps())
                && newWeight.equals(entity.getWeight());

        if (!sameTriple && repository.existsByExerciseIdAndSetAndRepsAndWeight(exerciseId, newSet, newReps, newWeight)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Duplicate result for this exercise");
        }

        mapper.update(entity, exerciseResultUpdateDTO);
        repository.save(entity);
        return mapper.toReadDTO(entity);
    }


    public void delete(UUID exerciseId, UUID id) {
        ensureExerciseExists(exerciseId);
        ExerciseResults result = repository.findByIdAndExerciseId(id, exerciseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Resul not found"));
        repository.delete(result);
    }

    private void ensureExerciseExists(UUID exerciseId) {
        if (!exerciseRepository.existsById(exerciseId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Exercise Not found");
        }
    }
}
