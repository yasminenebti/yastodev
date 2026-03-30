package com.yastodev.app.string.longestPalindromicSubstring;


class Solution {

    public String longestPalindromicSubstring(String s){
        if (s.length() <=1) {
            return s;
        }

        int maxLen = 1;
        String maxStr = s.substring(0,1);

        for (int i = 0; i < s.length() ; i++){
            for (int j = i + maxLen; j <= s.length() ; j++ ) {
                if (j - i > maxLen && isPalindrome(s.substring(i,j))){
                    maxLen = j - i;
                    maxStr = s.substring(i,j);
                }
            }
        }

        return maxStr;
    }

    private boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;

        while(left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }



    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.longestPalindromicSubstring("abaaaefdfeasa"));
        System.out.println(sol.longestPalindromicSubstring("ababa"));
        System.out.println(sol.longestPalindromicSubstring("abccbd"));
        System.out.println(sol.longestPalindromicSubstring("a"));
        System.out.println(sol.longestPalindromicSubstring("azerty"));


    }

}