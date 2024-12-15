package com.yastodev.app.functionalInterfaces.utils;

import java.time.LocalDate;

public class NutritionLog {
    private LocalDate date;
    private double totalCalories;
    private double protein;
    private double carbs;
    private double fats;

    public NutritionLog(LocalDate date, double totalCalories,
                        double protein, double carbs, double fats) {
        this.date = date;
        this.totalCalories = totalCalories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public double getTotalCalories() { return totalCalories; }
    public double getProtein() { return protein; }
    public double getCarbs() { return carbs; }
    public double getFats() { return fats; }
}
