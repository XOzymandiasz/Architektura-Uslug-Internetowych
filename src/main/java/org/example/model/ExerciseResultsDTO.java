package org.example.model;

import java.io.Serializable;

public class ExerciseResultsDTO implements Comparable<ExerciseResultsDTO>, Serializable {
    private Integer set;
    private Integer reps;
    private Integer weight;
    private Boolean personal_best;

    private String exercise_name;

    @Override
    public int compareTo(ExerciseResultsDTO object) {
        if (!this.set.equals(object.set))
            return this.set.compareTo(object.set);

        if (!this.reps.equals(object.reps))
            return this.reps.compareTo(object.reps);

        if (!this.weight.equals(object.weight))
            return this.weight.compareTo(object.weight);

        return Boolean.compare(this.personal_best, object.personal_best);
    }

    public static ExerciseResultsDTO from(ExerciseResults exerciseResults, String exerciseName) {
        ExerciseResultsDTO dto = new ExerciseResultsDTO();
        dto.set = exerciseResults.getSet();
        dto.reps = exerciseResults.getReps();
        dto.weight = exerciseResults.getWeight();
        dto.personal_best = exerciseResults.getPersonal_best();

        dto.exercise_name = exerciseName;

        return dto;
    }

    @Override
    public String toString() {
        String pr = this.personal_best ? "[Personal Best]" : "";
        return  this.exercise_name + "[Set" + set + ": " + reps + " x " + weight + " " + pr + "]";
    }
}
