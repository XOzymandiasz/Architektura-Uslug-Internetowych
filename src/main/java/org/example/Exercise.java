package org.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Exercise implements Comparable<Exercise> {
    private String name;
    private String muscleGroup;
    private String equipment;
    private int duration;

    private List<ExerciseResults> results;

    public void addResult(ExerciseResults result) {
        if (result == null) return;
        if (this.results == null) results = new ArrayList<>();
        results.add(result);
        result.setExercise(this);
    }

    @Override
    public int compareTo(Exercise object) {
        if (!this.name.equals(object.name))
            return this.name.compareTo(object.name);

        if (!this.muscleGroup.equals(object.muscleGroup))
            return this.muscleGroup.compareTo(object.muscleGroup);

        if (!this.equipment.equals(object.equipment))
            return this.equipment.compareTo(object.equipment);

        return Integer.compare(this.duration, object.duration);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Exercise)) return false;

        return this.name.equals(((Exercise) object).name)
                && this.muscleGroup.equals(((Exercise) object).muscleGroup)
                && this.equipment.equals(((Exercise) object).equipment)
                && this.duration == ((Exercise) object).duration;
    }

    @Override
    public String toString(){
        return "Name: " + this.name
                + " Muscle group: " + this.muscleGroup
                + " Needed equipment: " + this.equipment
                + " Recommended duration: " + this.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.muscleGroup, this.equipment);
    }

}
