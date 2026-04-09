package com.yastodev.app.MinWindowSubstring;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {

    public static String minWindowSubstringCheck(String[] strArr) {
        String N = strArr[0];
        String K = strArr[1];

        // Frequency map for characters needed from K
        Map<Character, Integer> need = new HashMap<>();
        for (char c : K.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int matched = 0;               // how many distinct chars are fully satisfied
        int required = need.size();    // how many distinct chars we need to satisfy

        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        Map<Character, Integer> window = new HashMap<>();

        while (right < N.length()) {
            // 1. Expand: add character at right into window
            char c = N.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // Check if this character's frequency now satisfies the need
            if (need.containsKey(c) && window.get(c).equals(need.get(c))) {
                matched++;
            }

            // 2. Shrink: move left pointer while window is valid
            while (matched == required) {
                // Update minimum window if current is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                // Remove left character from window
                char leftChar = N.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                    matched--;
                }
                left++;
            }

            right++;
        }

        return N.substring(minStart, minStart + minLen);
    }



}