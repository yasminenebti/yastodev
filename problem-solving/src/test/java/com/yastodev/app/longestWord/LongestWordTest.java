package com.yastodev.app.longestWord;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestWordTest {

    @Test
    void testLongestWordCheck() {
        assertEquals("time", LongestWord.LongestWordCheck("fun&!! time"));
        assertEquals("love", LongestWord.LongestWordCheck("I love dogs"));
        assertEquals("hello", LongestWord.LongestWordCheck("hello world!"));
        assertEquals("a", LongestWord.LongestWordCheck("a b c"));
        assertEquals("longest", LongestWord.LongestWordCheck("this is the longest word here"));
    }
}
