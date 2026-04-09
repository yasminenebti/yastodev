package com.yastodev.app.longestWord;


public class LongestWord {

    public static String LongestWordCheck(String sen) {
        String maxWord = "";
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < sen.length(); i++) {
            char c = sen.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                currentWord.append(c);
            } else {
                if (currentWord.length() > maxWord.length()) {
                    maxWord = currentWord.toString();
                }

                currentWord.setLength(0);
            }
        }
        if (currentWord.length() > maxWord.length()) {
            maxWord = currentWord.toString();
        }
        return maxWord;
    }


}
