package com.yastodev.app.functionalInterfaces.supplier;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.concurrent.ThreadLocalRandom;

public class ResilienceConfigSupplier {
    private static final Logger LOGGER = Logger.getLogger(ResilienceConfigSupplier.class.getName());

    /**
     * Represents a resilient configuration management system
     * that can fetch configurations with fallback and caching mechanisms.
     */
    public static class ConfigurationManager {
        // Cached configuration to avoid repeated expensive retrievals
        private Optional<AppConfiguration> cachedConfig = Optional.empty();

        /**
         * Supplies application configuration with multiple fallback strategies
         *
         * @return Supplier that provides application configuration
         */
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

        // Check if cached configuration is still fresh
        private boolean isConfigFresh() {
            // In a real-world scenario, implement actual freshness check
            return ThreadLocalRandom.current().nextBoolean();
        }
    }

    // Configuration data class
    public static class AppConfiguration {
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

    // Demonstration method
    public static void main(String[] args) {
        ResilienceConfigSupplier app = new ResilienceConfigSupplier();
        ConfigurationManager configManager = new ConfigurationManager();

        // Get the configuration supplier
        Supplier<AppConfiguration> configSupplier = configManager.getConfigurationSupplier();

        // Use the supplier multiple times to demonstrate fallback and caching
        for (int i = 0; i < 5; i++) {
            AppConfiguration config = configSupplier.get();
            System.out.println("Iteration " + (i + 1) + ": " + config);
        }
    }
}
