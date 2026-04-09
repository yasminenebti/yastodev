package com.yastodev.app.MinWindowSubstring;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinWindowSubstringTest {

    @Test
    void testMinWindowSubstringCheck() {
        assertEquals("dae", MinWindowSubstring.minWindowSubstringCheck(new String[]{"aaabaaddae", "aed"}));
        assertEquals("aabd", MinWindowSubstring.minWindowSubstringCheck(new String[]{"aabdccdbcacd", "aad"}));
        assertEquals("aksfaje", MinWindowSubstring.minWindowSubstringCheck(new String[]{"ahffaksfajeeubsne", "jefaa"}));
        assertEquals("affhkkse", MinWindowSubstring.minWindowSubstringCheck(new String[]{"aaffhkksemckelloe", "fhea"}));
    }
}
