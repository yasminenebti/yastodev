# Minimum Window Substring

## 🧩 Problem

Have the function MinWindowSubstring(strArr) take the strArr parameter being passed which will be an array of two strings. The first string in strArr will be a random string of letters, and the second string will be a series of letters that you need to find in the first string. Your goal is to return the smallest substring from the first string that contains all the letters from the second string in the same order. If there is no such substring, return the string "not possible".

Wait, correction: actually, it's the minimum window that contains all characters of the second string, not necessarily in order.

From the code, it's minimum window containing all characters of K, with frequencies.

## 📌 Examples

Input: ["aaabaaddae", "aed"]  
Output: "dae"

Input: ["aabdccdbcacd", "aad"]  
Output: "aabd"
