package org.example.exercise.infrastructure.mapper;

import org.example.exercise.api.request.ExerciseCreateRequest;
import org.example.exercise.api.request.ExerciseUpdateRequest;
import org.example.exercise.api.response.ExerciseListResponse;
import org.example.exercise.api.response.ExerciseReadResponse;
import org.example.exercise.domain.model.Exercise;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ExerciseMapper {

    /* =========================
       ENTITY -> DTO
       ========================= */

    public ExerciseReadResponse toReadDTO(Exercise exercise) {
        if (exercise == null) {
            return null;
        }

        return new ExerciseReadResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getMuscleGroup(),
                exercise.getEquipment(),
                exercise.getDuration()
        );
    }

    public ExerciseListResponse toListDTO(Exercise exercise) {
        if (exercise == null) {
            return null;
        }

        return new ExerciseListResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getMuscleGroup(),
                exercise.getEquipment(),
                exercise.getDuration()
        );
    }

    public List<ExerciseListResponse> toListDTOs(List<Exercise> exercises) {
        if (exercises == null) {
            return null;
        }

        List<ExerciseListResponse> list = new ArrayList<>(exercises.size());
        for (Exercise e : exercises) {
            list.add(toListDTO(e));
        }
        return list;
    }

    public List<ExerciseReadResponse> toReadDTOs(List<Exercise> exercises) {
        if (exercises == null) {
            return null;
        }

        List<ExerciseReadResponse> list = new ArrayList<>(exercises.size());
        for (Exercise e : exercises) {
            list.add(toReadDTO(e));
        }
        return list;
    }


    public Exercise toEntity(ExerciseCreateRequest dto) {
        if (dto == null) {
            return null;
        }

        UUID id = dto.id();

        return Exercise.builder()
                .id(id)
                .name(dto.name())
                .muscleGroup(dto.muscleGroup())
                .equipment(dto.equipment())
                .duration(dto.duration())
                .build();
    }


    public void update(Exercise entity, ExerciseUpdateRequest dto) {
        if (entity == null || dto == null) {
            return;
        }

        if (dto.name() != null) {
            entity.setName(dto.name());
        }
        if (dto.muscleGroup() != null) {
            entity.setMuscleGroup(dto.muscleGroup());
        }
        if (dto.equipment() != null) {
            entity.setEquipment(dto.equipment());
        }

        entity.setDuration(dto.duration());
    }
}
