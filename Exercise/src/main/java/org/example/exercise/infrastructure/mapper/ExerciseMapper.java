package org.example.exercise.infrastructure.mapper;

import org.example.exercise.api.request.ExerciseCreateRequest;
import org.example.exercise.api.request.ExerciseUpdateRequest;
import org.example.exercise.api.response.ExerciseListResponse;
import org.example.exercise.api.response.ExerciseReadResponse;
import org.example.exercise.domain.model.Exercise;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExerciseMapper {
    @Named("toReadDTO")
    ExerciseReadResponse toReadDTO(Exercise exercise);

    @Named("toListDTO")
    ExerciseListResponse toListDTO(Exercise exercises);

    List<ExerciseListResponse> toListDTOs(List<Exercise> exercises);

    @IterableMapping(qualifiedByName = "toReadDTO")
    List<ExerciseReadResponse> toReadDTOs(List<Exercise> exercises);

    Exercise toEntity(ExerciseCreateRequest readDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Exercise entity, ExerciseUpdateRequest dto);
}
