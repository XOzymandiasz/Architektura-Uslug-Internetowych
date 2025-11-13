package org.example.mapper;

import org.example.dto.exercise.request.ExerciseCreateRequest;
import org.example.dto.exercise.request.ExerciseUpdateRequest;
import org.example.dto.exercise.response.ExerciseListResponse;
import org.example.dto.exercise.response.ExerciseReadResponse;
import org.example.model.Exercise;
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
