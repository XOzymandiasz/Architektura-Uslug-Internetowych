package org.example.mapper;

import org.example.dto.exercise.result.request.ExerciseResultCreateRequest;
import org.example.dto.exercise.result.request.ExerciseResultUpdateRequest;
import org.example.dto.exercise.result.response.ExerciseResultListResponse;
import org.example.dto.exercise.result.response.ExerciseResultReadResponse;
import org.example.model.ExerciseResults;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExerciseResultsMapper {
    ExerciseResultListResponse toListDTO(ExerciseResults results);

    ExerciseResultReadResponse toReadDTO(ExerciseResults result);

    ExerciseResults toEntity(ExerciseResultCreateRequest result);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ExerciseResults entity, ExerciseResultUpdateRequest dto);
}
