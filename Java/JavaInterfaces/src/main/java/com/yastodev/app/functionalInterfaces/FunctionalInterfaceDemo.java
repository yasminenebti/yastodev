package com.yastodev.app.functionalInterfaces;

import java.util.function.Predicate;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {

        //Calculator add = (a,b) -> a + b;
        Calculator add = Integer::sum;

        Calculator multiply = (a,b) -> a * b;

        int addResult = add.calculate(5,3);
        int multiplyResult = multiply.calculate(5,3);

        System.out.println("Add result: " + addResult);
        System.out.println("Multiply result: " + multiplyResult);


    }
}
