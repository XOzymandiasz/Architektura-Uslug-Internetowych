package org.example.mapper;

import org.example.dto.exercise.result.request.ExerciseResultUpdateDTO;
import org.example.dto.exercise.result.response.ExerciseResultListDTO;
import org.example.dto.exercise.result.response.ExerciseResultReadDTO;
import org.example.model.ExerciseResults;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseResultsMapper {
    ExerciseResultListDTO toListDTO(ExerciseResults results);

    ExerciseResultReadDTO toReadDTO(ExerciseResults result);

    ExerciseResults toEntity(ExerciseResultReadDTO result);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget ExerciseResults entity, ExerciseResultUpdateDTO dto);
}
