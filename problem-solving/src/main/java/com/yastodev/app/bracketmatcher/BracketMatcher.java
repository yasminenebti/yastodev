package com.yastodev.app.bracketmatcher;

import java.util.Stack;

public class BracketMatcher {


    public static int bracketMatcherCheck(String str){
        final char openBracket = '(';
        final char closeBracket = ')';

        Stack<Character> bracketStack = new Stack<>();

        System.out.println();
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == openBracket) {
                bracketStack.push(openBracket);
            }
            else if (str.charAt(i) == closeBracket) {
                if (bracketStack.isEmpty()) {
                    return 0;
                }
                bracketStack.pop();
            }
        }

        return bracketStack.isEmpty() ? 1 : 0;
    }

}
