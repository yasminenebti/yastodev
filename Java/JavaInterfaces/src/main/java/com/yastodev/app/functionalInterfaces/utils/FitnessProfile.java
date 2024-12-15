package com.yastodev.app.functionalInterfaces.utils;

import java.util.ArrayList;
import java.util.List;

public class FitnessProfile {
    private String name;
    private int age;
    private double weight;
    private double height;
    private List<WorkoutSession> workoutHistory;
    private List<NutritionLog> nutritionLogs;

    public FitnessProfile(String name, int age, double weight, double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.workoutHistory = new ArrayList<>();
        this.nutritionLogs = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public void setWeight(double weight) { this.weight = weight; }
    public List<WorkoutSession> getWorkoutHistory() { return workoutHistory; }
    public List<NutritionLog> getNutritionLogs() { return nutritionLogs; }

    public void addWorkoutSession(WorkoutSession session) {
        workoutHistory.add(session);
    }

    public void addNutritionLog(NutritionLog log) {
        nutritionLogs.add(log);
    }

    @Override
    public String toString() {
        return "FitnessProfile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }


}
