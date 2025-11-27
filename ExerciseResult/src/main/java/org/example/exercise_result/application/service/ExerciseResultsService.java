package org.example.exercise_result.application.service;

import org.example.exercise_result.api.request.ExerciseResultCreateRequest;
import org.example.exercise_result.api.request.ExerciseResultUpdateRequest;
import org.example.exercise_result.api.response.ExerciseResultListResponse;
import org.example.exercise_result.api.response.ExerciseResultReadResponse;
import org.example.exercise_result.domain.model.ExerciseResults;
import org.example.exercise_result.infrastructure.mapper.ExerciseResultsMapper;
import org.example.exercise_result.domain.repository.ExerciseResultsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseResultsService {
    private final ExerciseResultsRepository repository;
    private final ExerciseResultsMapper mapper;

    public ExerciseResultsService(ExerciseResultsRepository repository, ExerciseResultsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<ExerciseResultListResponse> getALl(UUID exerciseId) {
        return repository.findByExerciseIdOrderBySetAscRepsAscWeightAsc(exerciseId)
                .stream()
                .map(mapper::toListDTO)
                .toList();
    }

    public ExerciseResultReadResponse create(UUID exerciseId, ExerciseResultCreateRequest exerciseResultCreateDTO) {
        if (repository.existsById(exerciseResultCreateDTO.id())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Result with given id already exists");
        }
        if (repository.existsByExerciseIdAndSetAndRepsAndWeight(
                exerciseId,
                exerciseResultCreateDTO.set(),
                exerciseResultCreateDTO.reps(),
                exerciseResultCreateDTO.weight()))
        {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Duplicate result for this exercise");
        }

        ExerciseResults entity = mapper.toEntity(exerciseResultCreateDTO);
        entity.setExerciseId(exerciseId);
        repository.save(entity);
        return mapper.toReadDTO(entity);
    }

    public ExerciseResultReadResponse update(UUID exerciseId, UUID resultId, ExerciseResultUpdateRequest exerciseResultUpdateDTO) {
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
        ExerciseResults result = repository.findByIdAndExerciseId(id, exerciseId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Resul not found"));
        repository.delete(result);
    }
}
