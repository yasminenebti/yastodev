package com.yastodev.app.functionalInterfaces.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class StartWithG {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Gabriel","Genesis","Elsa","Gavin","Emily");

        Predicate<String> startWithG = (name) -> name.startsWith("G");
        for (String name : names) {
            if (startWithG.test(name)){
                System.out.println("Name starts with G: " + name);
            }
        }
    }
}
