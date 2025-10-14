package org.example;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
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

    public static void nextQuest(String questName) {
        System.out.println(" ");
        for(int i = 0; i<50; i++) System.out.print("-");
        System.out.println(" ");
        System.out.println(questName);
        System.out.println(" ");
    }

    public static void runWithPool(List<ExerciseResultsDTO> exerciseResultsDTO, int parallel){
        ForkJoinPool forkJoinPool = new ForkJoinPool(parallel);
        long start = System.currentTimeMillis();
        Runnable task = () -> exerciseResultsDTO.parallelStream().forEach(exercise -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(Thread.currentThread().getName() + ": " + exercise);
        });

        try {
            forkJoinPool.submit(task).join();
        } finally {{
            forkJoinPool.shutdown();
            try {
                if(!forkJoinPool.awaitTermination(10, TimeUnit.SECONDS)) {
                    forkJoinPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                forkJoinPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }}
        long end = System.currentTimeMillis();
        System.out.println(" " + (end - start) + " ms");
    }

    public static void main(String[] args) {
        //second
        nextQuest("Second Quest");
        List<Exercise> exercises = fillElements();
        exercises.forEach(exercise -> {
            System.out.println(exercise);
            exercise.getResults().forEach(result -> {
               System.out.println(result);
            });
        });

        //third

        nextQuest("Third Quest");
        Set<ExerciseResults> set = exercises.stream()
                .flatMap(exercise -> exercise.getResults().stream())
                .collect(Collectors.toSet());
        System.out.println("Unique exercises");
        set.forEach(System.out::println);

        //fourth
        nextQuest("Fourth Quest");

        exercises.stream()
                .filter(exercise -> "Barbell".equals(exercise.getEquipment()))
                .sorted(Comparator.comparingInt(Exercise::getDuration).reversed())
                .forEach(System.out::println);

        //fifth
        nextQuest("Fifth Quest");

        List<ExerciseResultsDTO> exercisesResultsDTO = exercises.stream()
                .flatMap(exercise -> exercise.getResults().stream()
                    .map(results -> ExerciseResultsDTO.from(results, exercise.getName())))
                .sorted()
                .toList();

        exercisesResultsDTO.stream().forEach(System.out::println);

        //sixth
        nextQuest("Sixth Quest");
        String fileName = "exercises.bin";

        BinarySerializer.save(exercisesResultsDTO, fileName);

        List<ExerciseResultsDTO> loadedDTO = BinarySerializer.load(fileName);

        if (loadedDTO != null){
            loadedDTO.stream().forEach(System.out::println);
        }

        //seventh
        nextQuest("Seventh Quest");

        Integer[] poolSizes = {1, 2, 4, 8};

        for (int size : poolSizes){
            nextQuest("Size: " + size);
            runWithPool(loadedDTO, size);
        }

    }
}