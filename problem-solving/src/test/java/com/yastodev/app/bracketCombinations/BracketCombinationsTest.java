package com.yastodev.app.bracketCombinations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BracketCombinationsTest {

    @Test
    void testBracketCombinations() {
        assertEquals(1, BracketCombinations.bracketCombinations(1));
        assertEquals(2, BracketCombinations.bracketCombinations(2));
        assertEquals(5, BracketCombinations.bracketCombinations(3));
        assertEquals(14, BracketCombinations.bracketCombinations(4));
        assertEquals(1, BracketCombinations.bracketCombinations(0));
    }
}
