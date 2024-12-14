package com.yastodev.app.functionalInterfaces.consumer.settingsService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class SettingsBiConsumerService {
    public static void main(String[] args) {

        Map<String,String> userSettings = new HashMap<>();
        userSettings.put("theme","dark");
        userSettings.put("notifications","custom");
        userSettings.put("language","en");
        userSettings.put("privacy","public");


        System.out.println("Default User Settings: " + userSettings);
        System.out.println("----");

        Map<String, String> updates = new HashMap<>();
        updates.put("theme", "light");
        updates.put("privacy", "private");

        BiConsumer<String, String> updateSettings = (key, value) -> {
            System.out.println("Updating setting: " + key + " to " + value);
            userSettings.put(key, value);
        };

        processSettings(updates, updateSettings);

        System.out.println("----");
        System.out.println("Updated User Settings: " + userSettings);


    }

    public static void processSettings(Map<String, String> updates, BiConsumer<String, String> biConsumer) {
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }
}
