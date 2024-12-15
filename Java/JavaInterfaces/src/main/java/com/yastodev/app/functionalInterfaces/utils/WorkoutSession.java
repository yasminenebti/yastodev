package com.yastodev.app.functionalInterfaces.utils;

import java.time.LocalDate;

public class WorkoutSession {private String type;
    private int duration;
    private double caloriesBurned;
    private LocalDate date;

    public WorkoutSession(String type, int duration, double caloriesBurned, LocalDate date) {
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    // Getters
    public String getType() { return type; }
    public int getDuration() { return duration; }
    public double getCaloriesBurned() { return caloriesBurned; }
    public LocalDate getDate() { return date; }
}
