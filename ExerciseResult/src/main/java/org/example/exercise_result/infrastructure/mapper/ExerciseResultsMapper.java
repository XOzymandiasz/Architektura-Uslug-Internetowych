package org.example.exercise_result.infrastructure.mapper;

import org.example.exercise_result.api.request.ExerciseResultCreateRequest;
import org.example.exercise_result.api.request.ExerciseResultUpdateRequest;
import org.example.exercise_result.api.response.ExerciseResultListResponse;
import org.example.exercise_result.api.response.ExerciseResultReadResponse;
import org.example.exercise_result.domain.model.ExerciseResults;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExerciseResultsMapper {
    ExerciseResultListResponse toListDTO(ExerciseResults results);

    ExerciseResultReadResponse toReadDTO(ExerciseResults result);

    ExerciseResults toEntity(ExerciseResultCreateRequest result);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ExerciseResults entity, ExerciseResultUpdateRequest dto);
}
