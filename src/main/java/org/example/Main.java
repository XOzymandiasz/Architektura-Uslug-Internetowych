package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static List<Exercise> fillElements() {
        List<Exercise> exercises = new ArrayList<>();

        Exercise benchPress = Exercise.builder()
                .name("Bench Press")
                .muscleGroup("Chest")
                .equipment("Barbell")
                .duration(40)
                .build();

        benchPress.addResult(ExerciseResults.builder()
                .set(1)
                .reps(6)
                .weight(90)
                .personalBest(false)
                .build());

        benchPress.addResult(ExerciseResults.builder()
                .set(2)
                .reps(6)
                .weight(90)
                .personalBest(false)
                .build());

        benchPress.addResult(ExerciseResults.builder()
                .set(3)
                .reps(5)
                .weight(90)
                .personalBest(false)
                .build());

        exercises.add(benchPress);

        Exercise JMPress = Exercise.builder()
                .name("JM Press")
                .muscleGroup("Chest")
                .equipment("Barbell")
                .duration(20)
                .build();

        JMPress.addResult(ExerciseResults.builder()
                .set(1)
                .reps(8)
                .weight(70)
                .personalBest(true)
                .build());

        JMPress.addResult(ExerciseResults.builder()
                .set(2)
                .reps(8)
                .weight(75)
                .personalBest(true)
                .build());

        JMPress.addResult(ExerciseResults.builder()
                .set(3)
                .reps(5)
                .weight(80)
                .personalBest(false)
                .build());

        JMPress.addResult(ExerciseResults.builder()
                .set(4)
                .reps(3)
                .weight(80)
                .personalBest(false)
                .build());

        exercises.add(JMPress);

        return exercises;
    }

    public static void main(String[] args) {
        //second
        List<Exercise> exercises = fillElements();
        exercises.forEach(exercise -> {
            System.out.println(exercise);
            exercise.getResults().forEach(result -> {
               System.out.println(result);
            });
        });

        //third
        Set<ExerciseResults> set = exercises.stream()
                .flatMap(exercise -> exercise.getResults().stream())
                .collect(Collectors.toSet());
        System.out.println("Unique exercises");
        set.forEach(System.out::println);

        //fourth



    }
}