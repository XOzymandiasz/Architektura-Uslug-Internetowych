package org.example.mapper;

import org.example.dto.exercise.response.ExerciseReadDTO;
import org.example.model.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    ExerciseReadDTO toReadDTO(Exercise exercise);
    Exercise toCreateDTO(Exercise readDTO);
    Exercise toUpdateDTO(Exercise readDTO);
}
