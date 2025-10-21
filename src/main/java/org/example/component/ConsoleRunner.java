package org.example.component;

import org.example.model.ExerciseResults;
import org.springframework.context.ApplicationContext;
import org.example.model.Exercise;
import org.example.service.ExerciseResultsService;
import org.example.service.ExerciseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
@Order(2)
public class ConsoleRunner implements CommandLineRunner {
    private final ExerciseService exerciseService;
    private final ExerciseResultsService resultsService;
    private final ApplicationContext context;

    public ConsoleRunner(ExerciseService exerciseService,
                         ExerciseResultsService resultsService,
                         ApplicationContext context) {
        this.exerciseService = exerciseService;
        this.resultsService = resultsService;
        this.context = context;
    }

    private void showHelp() {
        System.out.println("--COMMANDS--");
        System.out.println("help - show help");
        System.out.println("listex - listing all exercises");
        System.out.println("listre - listing all exercise's results");
        System.out.println("addex <name> <muscle_group> <equipment> <duration> - add exercise");
        System.out.println("removeex <id> - remove exercise");
        System.out.println("addre <exercise> <set> <reps> <weight> <personal_best> - add result");
        System.out.println("removere <id> - remove result");
        System.out.println("exit - exit application");
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        showHelp();

        while (running) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];
            try{
                switch (command) {
                    case "listex":
                        listExercises();
                        break;
                    case "addex":
                        if (parts.length < 5) {
                            System.out.println("Usage: addex <name> <muscle_group> <equipment> <duration>");
                            break;
                        }
                        System.out.println("Adding exercise");
                        addExercise(UUID.randomUUID(), parts[1], parts[2], parts[3], Integer.valueOf(parts[4]));
                        break;
                    case "removeex":
                        if (parts.length < 2) {
                            System.out.println("Usage: removeex <id>");
                            break;
                        }
                        System.out.println("Remove exercise");
                        removeExercise(UUID.fromString(parts[1]));
                        break;
                    case "listre":
                        listResults();
                        break;
                    case "addre":
                        if (parts.length < 6) {
                            System.out.println("Usage: addre <exercise> <name> <muscle_group> <equipment> <duration>");
                            break;
                        }
                        System.out.println("Adding result");
                        addResult(UUID.randomUUID(),
                                parts[1],
                                Integer.valueOf(parts[2]),
                                Integer.valueOf(parts[3]),
                                Integer.valueOf(parts[4]),
                                Boolean.valueOf(parts[5]));
                        break;
                    case "removere":
                        if (parts.length < 2) {
                            System.out.println("Usage: removere <id>");
                            break;
                        }
                        System.out.println("Removing result");
                        removeResult(UUID.fromString(parts[1]));
                        break;
                    case "help":
                        showHelp();
                        break;
                    case "exit":
                        System.out.println("Exiting");
                        running = false;
                        SpringApplication.exit(context, () -> 0);
                        System.exit(0);
                        return;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void listExercises() {
        List<Exercise> exercises = exerciseService.getALl();
        if (exercises.isEmpty()) {
            System.out.println("No exercises found");
        } else {
            exercises.forEach(e -> System.out.println(
                    e.getId() + " | " +
                    e.getName() + " | " +
                    e.getMuscle_group() + " | " +
                    e.getEquipment() + " | " +
                    e.getDuration() + " | " + "ms"
            ));
        }
    }

    private void listResults() {
        List<ExerciseResults> results = resultsService.getALl();
        if (results.isEmpty()) {
            System.out.println("No exercises found");
        } else {
            results.forEach(e -> System.out.println(
                    e.getId() + " | " +
                            e.getSet() + " | " +
                            e.getReps() + " | " +
                            e.getWeight() + " | " +
                            e.getPersonal_best() + " | "
            ));
        }
    }

    private void addExercise(UUID id, String name, String muscle_group, String equipment, Integer duration) {
        Exercise exercise = Exercise.builder()
                .id(id)
                .name(name)
                .muscle_group(muscle_group)
                .equipment(equipment)
                .duration(duration)
                .build();
        exerciseService.save(exercise);
        System.out.println("Added exercise " + exercise.getId());
    }

    private void addResult(UUID id, String exercise, Integer set, Integer reps, Integer weight, Boolean personal_best) {
        Exercise choosedExercise = exerciseService.getByName(exercise);
        if (exercise == null) {
            System.out.println("Exercise " + choosedExercise + " not found. Not saving");
            return;
        }
        System.out.println(choosedExercise);
        ExerciseResults results = ExerciseResults.builder()
                .id(id)
                .set(set)
                .reps(reps)
                .weight(weight)
                .personal_best(personal_best)
                .build();
        choosedExercise.addResult(results);
        exerciseService.save(choosedExercise);
        System.out.println("Added exercise results " + results.getId());
    }

    private void removeExercise(UUID id) {
        exerciseService.deleteById(id);
        System.out.println("Deleted exercise " + id);
    }

    private void removeResult(UUID id) {
        resultsService.deleteById(id);
        System.out.println("Deleted exercise result " + id);
    }
}
