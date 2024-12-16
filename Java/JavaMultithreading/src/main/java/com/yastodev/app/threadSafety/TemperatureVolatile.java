package com.yastodev.app.threadSafety;

public class TemperatureVolatile {

    // Volatile ensures immediate visibility of changes across threads
    private double currentTemperature;
    private boolean isRunning = true;

    public void startMonitoring() {
        new Thread( () -> {
            while (isRunning) {
                currentTemperature = readTemperatureSensor();
                System.out.println("Current temperature: " + currentTemperature);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Temperature monitoring has stopped");
        }).start();
    }

    public void stopMonitoring() {
        isRunning = false;
    }

    private double readTemperatureSensor() {
        // Simulated sensor reading
        return Math.random() * 100;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public static void main(String[] args) {
        TemperatureVolatile temperatureVolatile = new TemperatureVolatile();

        System.out.println("Starting temperature monitoring...");
        temperatureVolatile.startMonitoring();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Stopping temperature monitoring...");
        temperatureVolatile.stopMonitoring();

        // Allow some time for the monitoring thread to stop
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print the last recorded temperature
        System.out.println("Last recorded temperature: " + temperatureVolatile.getCurrentTemperature());

    }
}
