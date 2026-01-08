package org.example.exercise.application.event;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class ExerciseEventClient {

    private final RestTemplate restTemplate;
    private final String exerciseResultBaseUrl = "http://exercise-result:5000";

    public ExerciseEventClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendExerciseCreated(UUID id, String name) {
        ExerciseEvent dto = new ExerciseEvent(id, name);
        String url = exerciseResultBaseUrl + "/internal/exercises";
        restTemplate.postForEntity(url, dto, Void.class);
    }

    public void sendExerciseDeleted(UUID id) {
        String url = exerciseResultBaseUrl + "/internal/exercises/" + id;
        restTemplate.delete(url);
    }
}
