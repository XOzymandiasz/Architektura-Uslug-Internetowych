package org.example.service;

import org.example.model.Exercise;
import org.example.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return repository.findByName(name).getFirst();
    }

    public void save(Exercise exercise) {
        if (exercise.getId() == null) {
            exercise.setId(UUID.randomUUID());
        }
        repository.save(exercise);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
