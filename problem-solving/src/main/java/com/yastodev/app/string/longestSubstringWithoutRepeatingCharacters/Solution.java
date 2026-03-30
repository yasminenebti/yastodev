package com.yastodev.app.string.longestSubstringWithoutRepeatingCharacters;

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

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // expected 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // expected 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // expected 3
    }
}