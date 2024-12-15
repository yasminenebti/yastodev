package com.yastodev.app.functionalInterfaces.function.fitnessProgram;

import com.yastodev.app.functionalInterfaces.utils.FitnessProfile;
import com.yastodev.app.functionalInterfaces.utils.WorkoutSession;

import java.util.List;
import java.util.function.BiFunction;

public class FitnessAnalyticsDemo {

        public static void main(String[] args) {
            FitnessAnalyticsService fitnessService = new FitnessAnalyticsService();

            // 1. Function Example: Extract Profile Names
            System.out.println("Profile Names:");
            List<String> profileNames = fitnessService.transformProfiles(FitnessProfile::getName);
            System.out.println(profileNames);

            // 2. BiFunction Example: Health Score Calculation
            System.out.println("\nHealth Score Calculation:");
            List<Double> healthScores = fitnessService.calculateHealthMetrics((weight, age) -> {
                // Custom health score calculation
                double bmi = weight / Math.pow(1.8, 2); // Assuming height of 180cm
                double ageFactory = 1 - (age / 100.0);
                return bmi * ageFactory;
            });
            System.out.println(healthScores);

            // 3. UnaryOperator Example: Weight Loss Program
            System.out.println("\nWeight Management:");
            List<FitnessProfile> weightAdjustedProfiles = fitnessService.adjustWeight(weight -> {
                // Apply weight loss strategy
                return weight * 0.95; // 5% weight reduction
            });
            weightAdjustedProfiles.forEach(System.out::println);

            // 4. BinaryOperator Example: Find Most Intense Workout
            System.out.println("\nMost Intense Workout:");
            WorkoutSession mostIntenseWorkout = fitnessService.findMostIntenseWorkout(
                    (workout1, workout2) ->
                            workout1.getCaloriesBurned() > workout2.getCaloriesBurned() ? workout1 : workout2
            );
            System.out.println("Most Intense: " + mostIntenseWorkout.getType() +
                    ", Calories Burned: " + mostIntenseWorkout.getCaloriesBurned());

            // 5. Advanced Nutrition Analysis
            System.out.println("\nNutrition Macro Analysis:");
            BiFunction<Double, Double, Double> macroBalanceScore = (protein, carbs) -> {
                // Calculate macro balance score
                double proteinRatio = protein / 120; // Ideal protein intake
                double carbRatio = carbs / 250;     // Ideal carb intake
                return (proteinRatio + carbRatio) / 2;
            };

            fitnessService.getProfiles().forEach(profile -> {
                profile.getNutritionLogs().forEach(log -> {
                    double macroScore = macroBalanceScore.apply(log.getProtein(), log.getCarbs());
                    System.out.println(profile.getName() + " - Date: " + log.getDate() +
                            ", Macro Balance Score: " + macroScore);
                });
            });
        }

}
