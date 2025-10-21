package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "exercise_results")
public class ExerciseResults implements Comparable<ExerciseResults> {
    @Id
    @Column(name="id", nullable=false, unique=true, columnDefinition = "UUID")
    private UUID id;
    @Column(name="which_set", nullable=false)
    private Integer set;
    @Column(name="reps", nullable=false)
    private Integer reps;
    @Column(name="weight", nullable=false)
    private Integer weight;
    @Column(name="personal_best", nullable=false)
    private Boolean personal_best = false;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @PrePersist
    protected void requireIdProvidedByUser() {
        if (id == null) throw new IllegalStateException("Id should be provided by user");
    }

    @Override
    public int compareTo(@NonNull ExerciseResults object) {
        if (!this.set.equals(object.set))
            return this.set.compareTo(object.set);

        if (!this.reps.equals(object.reps))
            return this.reps.compareTo(object.reps);

        if (!this.weight.equals(object.weight))
            return this.weight.compareTo(object.weight);

        return Boolean.compare(this.personal_best, object.personal_best);
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
        String pr = this.personal_best ? "[Personal Best]" : "";
        return "Exercise Result [Set" + set + ": " + reps + " x " + weight + " " + pr + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.set, this.reps, this.weight, this.personal_best);
    }
}
