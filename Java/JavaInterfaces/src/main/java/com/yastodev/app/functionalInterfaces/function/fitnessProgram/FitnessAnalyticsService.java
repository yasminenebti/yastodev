package com.yastodev.app.functionalInterfaces.function.fitnessProgram;

import com.yastodev.app.functionalInterfaces.utils.FitnessProfile;
import com.yastodev.app.functionalInterfaces.utils.NutritionLog;
import com.yastodev.app.functionalInterfaces.utils.WorkoutSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class FitnessAnalyticsService {
    private final List<FitnessProfile> profiles;

    public FitnessAnalyticsService() {
        profiles = new ArrayList<>();
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Create sample fitness profiles
        FitnessProfile john = new FitnessProfile("John", 30, 80.5, 180);
        john.addWorkoutSession(new WorkoutSession("Cardio", 45, 350, LocalDate.now().minusDays(1)));
        john.addWorkoutSession(new WorkoutSession("Strength", 60, 450, LocalDate.now().minusDays(3)));

        john.addNutritionLog(new NutritionLog(LocalDate.now().minusDays(1), 2200, 120, 250, 80));
        john.addNutritionLog(new NutritionLog(LocalDate.now().minusDays(2), 2100, 110, 240, 75));

        FitnessProfile lisa = new FitnessProfile("Lisa", 25, 60.5, 160);
        lisa.addWorkoutSession(new WorkoutSession("Cardio", 30, 250, LocalDate.now().minusDays(2)));
        lisa.addWorkoutSession(new WorkoutSession("zumba", 45, 350, LocalDate.now().minusDays(4)));

        lisa.addNutritionLog(new NutritionLog(LocalDate.now().minusDays(1), 1800, 100, 200, 60));
        lisa.addNutritionLog(new NutritionLog(LocalDate.now().minusDays(2), 1900, 110, 210, 65));

        profiles.add(john);
        profiles.add(lisa);
    }

    // Function Example: Profile Transformation
    public <R> List<R> transformProfiles(Function<FitnessProfile,R> transformer) {
        return profiles.stream()
                .map(transformer)
                .collect(Collectors.toList());
//        List<R> transformedProfiles = new ArrayList<>();
//        for (FitnessProfile profile : profiles) {
//            transformedProfiles.add(transformer.apply(profile));
//        }
//        return transformedProfiles;
    }

    public List<Double> calculateHealthMetrics(
            BiFunction<Double, Integer, Double> healthMetricCalculator) {
        return profiles.stream()
                .map(profile -> healthMetricCalculator.apply(profile.getWeight(), profile.getAge()))
                .collect(Collectors.toList());
    }

    public List<FitnessProfile> adjustWeight(UnaryOperator<Double> weightAdjustment){

//        return profiles.stream()
//                .map(profile -> {
//                    double adjustedWeight = weightAdjustment.apply(profile.getWeight());
//                    profile.setWeight(adjustedWeight);
//                    return profile;
//                })
//                .collect(Collectors.toList());
        return profiles.stream()
                .peek(profile -> profile.setWeight(weightAdjustment.apply(profile.getWeight())))
                .collect(Collectors.toList());
    }

    // BinaryOperator Example: Workout Intensity Comparison
    public WorkoutSession findMostIntenseWorkout(BinaryOperator<WorkoutSession> workoutComparator) {
        return profiles.stream()
                .flatMap(profile -> profile.getWorkoutHistory().stream())
                .reduce(workoutComparator)
                .orElse(null);
    }

    // Getter for profiles
    public List<FitnessProfile> getProfiles() {
        return new ArrayList<>(profiles);
    }



}
