package com.yastodev.app.findIntersection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindIntersectionTest {

    @Test
    void testFindIntersection() {
        assertEquals("1, 4, 13", FindIntersection.FindIntersectionCheck(new String[]{"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}));
        assertEquals("1, 9, 10", FindIntersection.FindIntersectionCheck(new String[]{"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"}));
        assertEquals("false", FindIntersection.FindIntersectionCheck(new String[]{"1, 2, 3", "4, 5, 6"}));
        assertEquals("5", FindIntersection.FindIntersectionCheck(new String[]{"5, 10", "5, 15"}));
    }
}
