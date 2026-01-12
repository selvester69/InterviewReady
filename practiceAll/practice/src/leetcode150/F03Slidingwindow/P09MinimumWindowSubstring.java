package leetcode150.F03Slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum
 * window substring of s such that every character in t (including duplicates)
 * is included in the window. If there is no such substring, return the empty
 * string "".
 * 
 * The testcases will be generated such that the answer is unique.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C'
 * from string t.
 * Example 2:
 * 
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * 
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * 
 * 
 * Constraints:
 * 
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 */
public class P09MinimumWindowSubstring {

    public static String minWindowSubString(String s, String t) {
        if (t == null || s == null || t.length() > s.length())
            return "";
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        Map<Character, Integer> tMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        int count = tMap.size();
        int i = 0, j = 0;
        while (j < s.length()) {
            // do calculations
            char ch = s.charAt(j);
            if (tMap.containsKey(ch)) {
                tMap.put(ch, tMap.get(ch) - 1);
                if (tMap.get(ch) == 0) {
                    count--;
                }
            }
            // window is achieved
            while (count == 0) {
                // get ans from calculations
                if(j-i+1<minLen){
                    minLen = j - i + 1;
                    start = i;
                }
                // slide the window
                char left = s.charAt(i);
                if (tMap.containsKey(left)) {
                    tMap.put(left, tMap.get(left) + 1);
                    if (tMap.get(left) > 0) {
                        count++;
                    }
                }
                i++;
            }
            j++;
        }
        return minLen==Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);

    }

    public static void main(String[] args) {
        System.out.println(minWindowSubString("ADOBECODEBANC", "ABC"));
    }
}