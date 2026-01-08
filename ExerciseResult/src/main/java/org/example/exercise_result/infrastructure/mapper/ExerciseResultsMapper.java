package org.example.exercise_result.infrastructure.mapper;

import org.example.exercise_result.api.request.ExerciseResultCreateRequest;
import org.example.exercise_result.api.request.ExerciseResultUpdateRequest;
import org.example.exercise_result.api.response.ExerciseResultListResponse;
import org.example.exercise_result.api.response.ExerciseResultReadResponse;
import org.example.exercise_result.domain.model.ExerciseResults;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExerciseResultsMapper {
    public ExerciseResultListResponse toListDTO(ExerciseResults entity) {
        if (entity == null) {
            return null;
        }

        return new ExerciseResultListResponse(
                entity.getId(),
                entity.getSet(),
                entity.getReps(),
                entity.getWeight(),
                entity.getPersonalBest()
        );
    }

    public ExerciseResultReadResponse toReadDTO(ExerciseResults entity) {
        if (entity == null) {
            return null;
        }

        return new ExerciseResultReadResponse(
                entity.getId(),
                entity.getSet(),
                entity.getReps(),
                entity.getWeight(),
                entity.getPersonalBest()
        );
    }

    public ExerciseResults toEntity(ExerciseResultCreateRequest dto) {
        if (dto == null) {
            return null;
        }

        return ExerciseResults.builder()
                .id(resolveId(dto))
                .set(dto.set())
                .reps(dto.reps())
                .weight(dto.weight())
                .personalBest(dto.personalBest())
                .build();
    }

    public void update(ExerciseResults entity, ExerciseResultUpdateRequest dto) {
        if (entity == null || dto == null) {
            return;
        }

        if (dto.set() != null) {
            entity.setSet(dto.set());
        }
        if (dto.reps() != null) {
            entity.setReps(dto.reps());
        }
        if (dto.weight() != null) {
            entity.setWeight(dto.weight());
        }
        entity.setPersonalBest(dto.personalBest());
    }

    private UUID resolveId(ExerciseResultCreateRequest dto) {
        if (dto.id() != null) {
            return dto.id();
        }

        throw new IllegalStateException("ExerciseResultCreateRequest.id is required");
    }
}
