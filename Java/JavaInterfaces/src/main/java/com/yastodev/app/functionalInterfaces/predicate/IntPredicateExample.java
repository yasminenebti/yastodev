package com.yastodev.app.functionalInterfaces.predicate;

import java.util.function.IntPredicate;

public class IntPredicateExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        IntPredicate isEven = number -> number % 2 == 0;
        IntPredicate isDivisibleBy3 = number -> number % 3 == 0;

        for (int number : numbers){
            if (isEven.and(isDivisibleBy3).test(number)){
                System.out.println("This number is even and divisible by 3 :" + number);
            }
        }

        int[] ages = {15, 21, 17, 30};

        // Define an IntPredicate to check if age is above 18
        IntPredicate isAdult = age -> age >= 18;

        // Validate and print results
        for (int age : ages) {
            System.out.println("Age " + age + " is adult? " + isAdult.test(age));
        }
    }
}
