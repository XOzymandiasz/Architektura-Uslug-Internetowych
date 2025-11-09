package org.example.mapper;

import org.example.dto.exercise.result.request.ExerciseResultCreateDTO;
import org.example.dto.exercise.result.request.ExerciseResultUpdateDTO;
import org.example.dto.exercise.result.response.ExerciseResultListDTO;
import org.example.dto.exercise.result.response.ExerciseResultReadDTO;
import org.example.model.ExerciseResults;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseResultsMapper {
    ExerciseResultCreateDTO toCreateDTO(ExerciseResults result);
    ExerciseResultUpdateDTO toUpdateDTO(ExerciseResults result);
    ExerciseResultListDTO toListDTO(List<ExerciseResults> results);
    ExerciseResultReadDTO toReadDTO(ExerciseResults result);
}
