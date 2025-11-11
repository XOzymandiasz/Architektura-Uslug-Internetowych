package org.example.mapper;

import org.example.dto.exercise.request.ExerciseCreateDTO;
import org.example.dto.exercise.request.ExerciseUpdateDTO;
import org.example.dto.exercise.response.ExerciseReadDTO;
import org.example.model.Exercise;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    ExerciseReadDTO toReadDTO(Exercise exercise);

    @IterableMapping(qualifiedByName = "toReadDTO")
    List<ExerciseReadDTO> toReadDTOs(List<Exercise> exercises);

    Exercise toEntity(ExerciseCreateDTO readDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Exercise entity, ExerciseUpdateDTO dto);
}
