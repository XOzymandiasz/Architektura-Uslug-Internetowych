package org.example.service;

import org.example.models.Exercise;
import org.example.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository repository;

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public List<Exercise> getALl() {
        return repository.findAll();
    }

    public Exercise getByName(String name) {
        return (Exercise) repository.findByName(name);
    }

}
