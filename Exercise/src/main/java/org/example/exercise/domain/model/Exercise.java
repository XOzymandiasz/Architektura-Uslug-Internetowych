package org.example.exercise.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
    private String muscleGroup;
    @Column(name="equipment", nullable=false)
    private String equipment;
    @Column(name="duration", nullable=false)
    private int duration;

    @PrePersist
    protected void requireIdProvidedByUser() {
        if (id == null) throw new IllegalStateException("Id should be provided by user");
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
                + " Muscle group: " + this.muscleGroup + ", "
                + " Needed equipment: " + this.equipment + ", "
                + " Recommended duration: " + this.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.muscleGroup, this.equipment, this.duration);
    }

}
