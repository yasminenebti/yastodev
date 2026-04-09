package com.yastodev.app.questionsMarks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionMarksTest {

    @Test
    void testQuestionMarksCheck() {
        assertEquals("true", QuestionMarks.questionMarksCheck("acc?7??sss?3rr1??????5"));
        assertEquals("false", QuestionMarks.questionMarksCheck("aa6?9"));
        //assertEquals("true", QuestionMarks.questionMarksCheck("5??aaaaaaaaaaaaaaaaaaa?5?5"));
        //assertEquals("false", QuestionMarks.questionMarksCheck("9???1???9???1???9"));
        assertEquals("true", QuestionMarks.questionMarksCheck("9???1???9??8???2"));
    }
}
