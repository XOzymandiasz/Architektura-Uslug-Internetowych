package org.example;

import java.util.List;
import java.util.Objects;

public class Exercise implements Comparable<Exercise> {
    private String name;
    private String muscleGroup;
    private String equipment;

    private List<ExerciseResults> results;

    @Override
    public int compareTo(Exercise o) {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Exercise)) {
            return false;
        }
        return Objects.equals(((Exercise) object).name, this.name)
                && Objects.equals(((Exercise) object).muscleGroup, this.muscleGroup)
                && Objects.equals(((Exercise) object).equipment, this.equipment);
    }

    @Override
    public String toString(){
        return this.name + " " + this.muscleGroup + " " + this.equipment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.muscleGroup, this.equipment);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMuscleGroup() {
        return muscleGroup;
    }
    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
