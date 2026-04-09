package com.yastodev.app.bracketCombinations;

public class BracketCombinations {

    public static int bracketCombinations(int num) {
        int[] count = {0};
        generate(num, num, count);
        return count[0];
    }

    private static void generate(int open, int close, int[] count) {
        if (open == 0 && close == 0) {
            count[0]++;
            return;
        }
        if (open > 0) {
            generate(open - 1, close, count);
        }
        if (close > open) {
            generate(open, close - 1, count);
        }
    }
}
