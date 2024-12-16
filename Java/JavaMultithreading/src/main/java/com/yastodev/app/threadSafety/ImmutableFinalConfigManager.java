package com.yastodev.app.threadSafety;

public class ImmutableFinalConfigManager {
    // Final ensures the reference cannot be changed after initialization
    private final String serverUrl;
    private final int maxConnections;
    private final boolean debugMode;

    // Constructor initializes final fields
    public ImmutableFinalConfigManager(String serverUrl, int maxConnections, boolean debugMode) {
        this.serverUrl = serverUrl;
        this.maxConnections = maxConnections;
        this.debugMode = debugMode;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public static void main(String[] args) {
        // Thread-safe configuration sharing
        ImmutableFinalConfigManager config = new ImmutableFinalConfigManager(
                "https://api.example.com",
                100,
                true
        );

        // Multiple threads can safely read the configuration
        new Thread(() -> {
            System.out.println("Thread 1 - Server URL: " + config.getServerUrl());
        }).start();

        new Thread(() -> {
            System.out.println("Thread 2 - Max Connections: " + config.getMaxConnections());
        }).start();
    }


}
