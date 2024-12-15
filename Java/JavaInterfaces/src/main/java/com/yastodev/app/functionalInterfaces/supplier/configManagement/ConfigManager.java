package com.yastodev.app.functionalInterfaces.supplier.configManagement;

import com.yastodev.app.functionalInterfaces.utils.AppConfiguration;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class ConfigManager {
    private static final Logger LOGGER = Logger.getLogger(ConfigManager.class.getName());

    private Optional<AppConfiguration> cachedConfig = Optional.empty();

    public Supplier<AppConfiguration> getConfigurationSupplier() {
        return () -> {
            // First, try to return cached configuration if available
            if (cachedConfig.isPresent() && isConfigFresh()) {
                return cachedConfig.get();
            }

            // Primary configuration source
            Supplier<AppConfiguration> primaryConfigSupplier = this::fetchFromPrimaryConfigService;

            // Backup configuration sources with fallback mechanism
            Supplier<AppConfiguration> secondaryConfigSupplier = this::fetchFromSecondaryConfigService;
            Supplier<AppConfiguration> defaultConfigSupplier = this::createDefaultConfiguration;

            try {
                // Attempt to get configuration from primary source
                AppConfiguration config = primaryConfigSupplier.get();

                // Cache and return the configuration
                cachedConfig = Optional.of(config);
                return config;
            } catch (Exception primarySourceException) {
                LOGGER.warning("Primary config source failed: " + primarySourceException.getMessage());

                try {
                    // Fallback to secondary configuration source
                    AppConfiguration config = secondaryConfigSupplier.get();
                    cachedConfig = Optional.of(config);
                    return config;
                } catch (Exception secondarySourceException) {
                    LOGGER.warning("Secondary config source failed: " + secondarySourceException.getMessage());

                    // Last resort: use default configuration
                    AppConfiguration defaultConfig = defaultConfigSupplier.get();
                    cachedConfig = Optional.of(defaultConfig);
                    return defaultConfig;
                }
            }
        };
    }

    // Check if cached configuration is still fresh
    private boolean isConfigFresh() {
        // In a real-world scenario, implement actual freshness check
        return ThreadLocalRandom.current().nextBoolean();
    }

    // Simulate fetching from primary configuration service
    private AppConfiguration fetchFromPrimaryConfigService() {
        // Simulate potential failure with random exception
        if (ThreadLocalRandom.current().nextInt(10) < 3) {
            throw new RuntimeException("Primary config service unavailable");
        }
        return new AppConfiguration("primary", 100, true);
    }

    // Simulate fetching from secondary configuration service
    private AppConfiguration fetchFromSecondaryConfigService() {
        // Simulate potential failure with random exception
        if (ThreadLocalRandom.current().nextInt(10) < 5) {
            throw new RuntimeException("Secondary config service unavailable");
        }
        return new AppConfiguration("secondary", 80, false);
    }

    // Create a default configuration when all other sources fail
    private AppConfiguration createDefaultConfiguration() {
        return new AppConfiguration("default", 50, false);
    }


}
