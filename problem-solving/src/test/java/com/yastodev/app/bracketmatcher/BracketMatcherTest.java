package com.yastodev.app.bracketmatcher;


import org.junit.jupiter.api.Test;

import static com.yastodev.app.bracketmatcher.BracketMatcher.bracketMatcherCheck;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BracketMatcherTest {

    @Test
    void testBracketMatcher() {

        assertEquals(0, bracketMatcherCheck("(coder)(byte))"));
        assertEquals(1, bracketMatcherCheck("(c(oder)) b(yte)"));

        // No brackets
        assertEquals(1, bracketMatcherCheck("hello world"));
        assertEquals(1, bracketMatcherCheck("abc123"));

        // Simple valid
        assertEquals(1, bracketMatcherCheck("()"));
        assertEquals(1, bracketMatcherCheck("(())"));
        assertEquals(1, bracketMatcherCheck("(()())"));

        // Simple invalid
        assertEquals(0, bracketMatcherCheck("("));
        assertEquals(0, bracketMatcherCheck(")"));
        assertEquals(0, bracketMatcherCheck("(()"));
        assertEquals(0, bracketMatcherCheck("())"));

        // Same count but wrong order
        assertEquals(0, bracketMatcherCheck(")("));
        assertEquals(0, bracketMatcherCheck("())("));
        assertEquals(0, bracketMatcherCheck(")()("));
        assertEquals(0, bracketMatcherCheck("())(()"));

        // Nested with text
        assertEquals(1, bracketMatcherCheck("(hello (world))"));
        assertEquals(1, bracketMatcherCheck("((hello (world)))"));
        assertEquals(0, bracketMatcherCheck("((hello (world))"));
        assertEquals(0, bracketMatcherCheck("(hello (world)))"));

        // Mixed positions
        assertEquals(1, bracketMatcherCheck("a(b)c(d)e"));
        assertEquals(1, bracketMatcherCheck("a(b(c)d)e"));
        assertEquals(0, bracketMatcherCheck("a(b(c)d"));
        assertEquals(0, bracketMatcherCheck("a)b(c)d("));

        // Edge tricky cases
        assertEquals(1, bracketMatcherCheck(""));
        assertEquals(1, bracketMatcherCheck("((((()))))"));
        assertEquals(0, bracketMatcherCheck("((((())))))"));
        assertEquals(1, bracketMatcherCheck("((()()()))"));
        assertEquals(0, bracketMatcherCheck("((()())()"));

        // Many brackets
        assertEquals(1, bracketMatcherCheck("()()()()"));
        assertEquals(0, bracketMatcherCheck("()()()())"));
        assertEquals(1, bracketMatcherCheck("((())())()"));
        assertEquals(0, bracketMatcherCheck("((())())())"));

        // Complex invalid
        assertEquals(0, bracketMatcherCheck("())())"));
        assertEquals(0, bracketMatcherCheck("(()))(()"));
        assertEquals(0, bracketMatcherCheck(")()()()("));

        System.out.println("✅ All tests passed successfully!");
    }

}