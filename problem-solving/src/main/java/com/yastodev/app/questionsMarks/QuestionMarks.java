package com.yastodev.app.questionsMarks;


public class QuestionMarks {

    public static String questionMarksCheck(String str){
        boolean foundPair = false;
        int lastDigitIndex = -1;
        int lastDigit = -1;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                int currentDigit = c - '0';

                if (lastDigit != -1) {
                    int countQuestions = 0;

                    for (int j = lastDigitIndex +1 ; j < i ; j++){
                        if (str.charAt(j) == '?') countQuestions++;
                    }
                    if (lastDigit + currentDigit == 10) {
                        foundPair = true;
                        if (countQuestions != 3) return "false";
                    }
                }


                lastDigit = currentDigit;
                lastDigitIndex = i;



            }

        }
        return foundPair ? "true" : "false";

    }


}
