package org.example;

import java.util.Objects;

public class ExerciseResults implements Comparable<ExerciseResults> {
    private Integer set;
    private Integer reps;
    private Integer weight;

    @Override
    public int compareTo(ExerciseResults object) {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return false;
        }
        if (!(object instanceof ExerciseResults)) {
            return false;
        }
        return this.set.equals(((ExerciseResults) object).set)
                && this.reps.equals(((ExerciseResults) object).reps)
                && this.weight.equals(((ExerciseResults) object).weight);
    }

    @Override
    public String toString() {
        return "ExerciseResults [Set" + set + ": " + reps + " x " + weight + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.set);
    }


}
