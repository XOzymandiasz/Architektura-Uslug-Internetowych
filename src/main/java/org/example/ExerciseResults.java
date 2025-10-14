package org.example;

import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ExerciseResults implements Comparable<ExerciseResults> {
    private Integer set;
    private Integer reps;
    private Integer weight;
    private Boolean personalBest;

    private Exercise exercise;

    @Override
    public int compareTo(ExerciseResults object) {
        if (!this.set.equals(object.set))
            return this.set.compareTo(object.set);

        if (!this.reps.equals(object.reps))
            return this.reps.compareTo(object.reps);

        if (!this.weight.equals(object.weight))
            return this.weight.compareTo(object.weight);

        return Boolean.compare(this.personalBest, object.personalBest);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ExerciseResults)) return false;
        
        return this.set.equals(((ExerciseResults) object).set)
                && this.reps.equals(((ExerciseResults) object).reps)
                && this.weight.equals(((ExerciseResults) object).weight);
    }

    @Override
    public String toString() {
        String pr = this.personalBest ? "[Personal Best]" : "";
        return "Exercise Result [Set" + set + ": " + reps + " x " + weight + " " + pr + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.set, this.reps, this.weight, this.personalBest);
    }
}
