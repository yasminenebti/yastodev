package com.yastodev.app.longestWord;

import java.util.Arrays;
import java.util.Comparator;

public class LongestWordStream {

    public static String LongestWordCheck(String sen) {
        return Arrays.stream(sen.split("[^a-zA-Z0-9]+"))
                .max(Comparator.comparingInt(String::length)) //(s1,s2) -> Integer.compare(s1.length(), s2.length())
                .orElse("");
    }

    public static void main(String[] args) {
        System.out.println(LongestWordCheck("fun&!! time"));
        System.out.println(LongestWordCheck("I love dogs"));
    }
}
