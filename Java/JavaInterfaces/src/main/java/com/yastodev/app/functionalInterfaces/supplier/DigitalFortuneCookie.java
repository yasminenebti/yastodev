package com.yastodev.app.functionalInterfaces.supplier;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class DigitalFortuneCookie {

    public static void main(String[] args) {
        // List of fortunes
        List<String> fortunes = Arrays.asList(
                "Today is a great day to start something new!",
                "Your efforts will soon pay off.",
                "Happiness is just around the corner.",
                "Believe in yourself, and great things will happen.",
                "An exciting opportunity lies ahead.",
                "You will make someone smile today.",
                "The answer you seek is within you."
        );

        // Supplier for generating random fortunes
        Supplier<String> fortuneSupplier = () -> {
            Random random = new Random();
            return fortunes.get(random.nextInt(fortunes.size()));
        };

        // Simulate a user clicking the "Get Fortune" button multiple times
        for (int i = 0; i < 1; i++) {
            System.out.println("Fortune: " + fortuneSupplier.get());
        }
    }
}
