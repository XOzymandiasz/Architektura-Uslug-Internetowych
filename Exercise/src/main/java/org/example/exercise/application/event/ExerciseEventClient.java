package org.example.exercise.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ExerciseEventClient {

    private final RestTemplate restTemplate;

    private String exerciseResultBaseUrl;

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
