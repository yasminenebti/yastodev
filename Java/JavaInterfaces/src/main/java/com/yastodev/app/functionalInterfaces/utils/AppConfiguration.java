package com.yastodev.app.functionalInterfaces.utils;

public class AppConfiguration {
    private final String source;
    private final int timeout;
    private final boolean isProduction;

    public AppConfiguration(String source, int timeout, boolean isProduction) {
        this.source = source;
        this.timeout = timeout;
        this.isProduction = isProduction;
    }

    @Override
    public String toString() {
        return "AppConfiguration{" +
                "source='" + source + '\'' +
                ", timeout=" + timeout +
                ", isProduction=" + isProduction +
                '}';
    }



}
