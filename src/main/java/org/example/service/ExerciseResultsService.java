package org.example.service;

import org.example.model.ExerciseResults;
import org.example.repository.ExerciseResultsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseResultsService {
    private final ExerciseResultsRepository repository;
    public ExerciseResultsService(ExerciseResultsRepository repository) {
        this.repository = repository;
    }

    public List<ExerciseResults> getALl() {
        return repository.findAll();
    }

    public void save(ExerciseResults results) {
        if (results.getId() == null) {
            results.setId(UUID.randomUUID());
        }
        repository.save(results);
    }

    @Transactional
    public void deleteById(UUID id) {
        repository.hardDeleteById(id);
        repository.flush();
    }
}
