package org.example.service;

import org.example.models.ExerciseResults;
import org.example.repository.ExerciseResultsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseResultsService {
    private final ExerciseResultsRepository repository;
    public ExerciseResultsService(ExerciseResultsRepository repository) {
        this.repository = repository;
    }

    public List<ExerciseResults> getALl() {
        return repository.findAll();
    }
}
