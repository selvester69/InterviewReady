package leetcode150.F03Slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without duplicate
 * characters.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and
 * "cab" are also correct answers.
 * Example 2:
 * 
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * 
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a
 * substring.
 * 
 * 
 * Constraints:
 * 
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */

public class P07LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring_brute(String s) {
        if (s == null || s.length() == 0)
            return 0;
        // generate all possible substring
        int longest = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                Set<Character> set = new HashSet<>();
                for (char ch : sub.toCharArray()) {
                    set.add(ch);
                }
                if (set.size() == sub.length()) {
                    longest = Math.max(longest, sub.length());
                }
            }
        }
        return longest;
    }

    public static int lengthOfLongestSubstring(String s) {
        int longest = Integer.MIN_VALUE;
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            char ch = s.charAt(j);
            if (!set.contains(ch)) {
                set.add(ch);
                longest = Math.max(longest, set.size());
                j++;
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("dvdf")); // 3
    }

}