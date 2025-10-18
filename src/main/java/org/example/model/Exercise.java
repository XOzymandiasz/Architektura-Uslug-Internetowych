package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "exercise")
public class Exercise implements Comparable<Exercise>, Serializable {
    @Id
    @Column(name="id", nullable=false, unique=true, columnDefinition = "UUID")
    private UUID id;
    @Column(name="name", nullable=false, unique = true)
    private String name;
    @Column(name="muscle_group", nullable=false)
    private String muscle_group;
    @Column(name="equipment", nullable=false)
    private String equipment;
    @Column(name="duration", nullable=false)
    private int duration;

    @Builder.Default
    @OneToMany(mappedBy="exercise", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<ExerciseResults> results = new ArrayList<>();


    @PrePersist
    protected void requireIdProvidedByUser() {
        if (id == null) throw new IllegalStateException("Id should be provided by user");
    }

    public void addResult(ExerciseResults result) {
        results.add(result);
        result.setExercise(this);
    }

    public void removeREsult(ExerciseResults result) {
        results.remove(result);
        result.setExercise(null);
    }

    @Override
    public int compareTo(Exercise object) {
        if (!this.name.equals(object.name))
            return this.name.compareTo(object.name);

        if (!this.muscle_group.equals(object.muscle_group))
            return this.muscle_group.compareTo(object.muscle_group);

        if (!this.equipment.equals(object.equipment))
            return this.equipment.compareTo(object.equipment);

        return Integer.compare(this.duration, object.duration);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Exercise)) return false;

        return this.name.equals(((Exercise) object).name)
                && this.muscle_group.equals(((Exercise) object).muscle_group)
                && this.equipment.equals(((Exercise) object).equipment)
                && this.duration == ((Exercise) object).duration;
    }

    @Override
    public String toString(){
        return "Name: " + this.name
                + " Muscle group: " + this.muscle_group + ", "
                + " Needed equipment: " + this.equipment + ", "
                + " Recommended duration: " + this.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.muscle_group, this.equipment, this.duration);
    }

}
