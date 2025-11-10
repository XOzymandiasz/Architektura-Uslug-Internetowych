package org.example.controllers;

import org.example.dto.exercise.response.ExerciseReadDTO;
import org.example.service.ExerciseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {
    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    public List<ExerciseReadDTO> getAll() {
        return service.getALl();
    }
}
