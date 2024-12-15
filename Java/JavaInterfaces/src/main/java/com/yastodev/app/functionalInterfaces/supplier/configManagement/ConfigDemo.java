package com.yastodev.app.functionalInterfaces.supplier.configManagement;

import com.yastodev.app.functionalInterfaces.supplier.ResilienceConfigSupplier;
import com.yastodev.app.functionalInterfaces.utils.AppConfiguration;

import java.util.function.Supplier;

public class ConfigDemo {
    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        System.out.println("Configuration: " + configManager.getConfigurationSupplier().get());

        Supplier<AppConfiguration> configurationSupplier = configManager.getConfigurationSupplier();
        // Use the supplier multiple times to demonstrate fallback and caching
        for (int i = 0; i < 5; i++) {
            AppConfiguration config = configurationSupplier.get();
            System.out.println("Iteration " + (i + 1) + ": " + config);
        }
    }
}
