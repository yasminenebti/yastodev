package com.yastodev.app.LongestSubstringWithoutRepeatingCharacters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    @Test
    void testLengthOfLongestSubstring() {
        Solution sol = new Solution();
        assertEquals(3, sol.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, sol.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, sol.lengthOfLongestSubstring("pwwkew"));
        assertEquals(1, sol.lengthOfLongestSubstring("a"));
        assertEquals(2, sol.lengthOfLongestSubstring("aab"));
    }
}
