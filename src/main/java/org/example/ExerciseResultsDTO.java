package org.example;

public class ExerciseResultsDTO implements Comparable<ExerciseResultsDTO> {
    private Integer set;
    private Integer reps;
    private Integer weight;
    private Boolean personalBest;

    private String exerciseName;

    @Override
    public int compareTo(ExerciseResultsDTO object) {
        if (!this.set.equals(object.set))
            return this.set.compareTo(object.set);

        if (!this.reps.equals(object.reps))
            return this.reps.compareTo(object.reps);

        if (!this.weight.equals(object.weight))
            return this.weight.compareTo(object.weight);

        return Boolean.compare(this.personalBest, object.personalBest);
    }

    @Override
    public String toString() {
        String pr = this.personalBest ? "[Personal Best]" : "";
        return  this.exerciseName + "[Set" + set + ": " + reps + " x " + weight + " " + pr + "]";
    }
}
