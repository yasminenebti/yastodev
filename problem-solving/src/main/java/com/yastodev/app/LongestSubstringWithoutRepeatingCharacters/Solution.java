package com.yastodev.app.LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();

        int start = 0;
        int result = 0;

        Map<Character,Integer> charIndexMap = new HashMap<>();

        for (int charIndex = 0 ; charIndex < n ; charIndex++) {
            char currentChar = s.charAt(charIndex);

            if (charIndexMap.containsKey(currentChar)) {
                start = Math.max(start, charIndexMap.get(currentChar) + 1);
            }

            result = Math.max(result, charIndex - start + 1);
            charIndexMap.put(currentChar, charIndex);

        }
        return result;
    }

}