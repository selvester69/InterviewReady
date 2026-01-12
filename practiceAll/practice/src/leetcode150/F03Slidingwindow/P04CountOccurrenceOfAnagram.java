package leetcode150.F03Slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a word pattern and a string text consisting of lowercase characters,
 * the task is to return the count of substrings in text which are anagrams of
 * the pattern.
 * 
 * Examples:
 * 
 * Input : text = "forxxorfxdofr", pattern = "for"
 * Output : 3
 * Explanation : Anagrams present are for, orf and ofr. Each appears in the text
 * once and hence the count is 3.
 * 
 * Input : text = "aabaabaa", pattern = "aaba"
 * Output : 4
 * Explanation : Anagrams present are aaba and abaa. Each appears twice in the
 * text and hence the count is 4.
 */
public class P04CountOccurrenceOfAnagram {

    public static boolean validAnagramUtil(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] charMap = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charMap[s.charAt(i) - 'a']++;
            charMap[t.charAt(i) - 'a']--;
        }
        for (int f : charMap) {
            if (f != 0) {
                return false;
            }
        }
        return true;
    }

    public static int countAnagrams_brute(String text, String pattern) {
        int count = 0;
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) { // ensure +1 to correct count.
            String sub = text.substring(i, i + pattern.length());
            if (validAnagramUtil(sub, pattern)) {
                count++;
            }
        }
        return count;
    }

    // applying sliding window
    public static int countAnagrams_Optimised(String text, String pattern) {
        if (pattern.length() > text.length())
            return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int count = map.size();
        int i = 0, j = 0;
        while (j < text.length()) {
            // calculations
            char ch = text.charAt(j);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) {
                    count--;
                }
            }
            if (j - i + 1 < pattern.length()) {
                j++;
            } else if (j - i + 1 == pattern.length()) {
                // get ans from calculations
                if (count == 0) {
                    res++;
                }
                // slide the window
                if (map.containsKey(text.charAt(i))) {
                    map.put(text.charAt(i), map.get(text.charAt(i)) + 1);
                    if (map.get(text.charAt(i)) == 1) {
                        count++;
                    }
                }
                i++;
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countAnagrams_brute("forxxorfxdofr", "for"));
        System.out.println(countAnagrams_Optimised("forxxorfxdofr", "for"));
        System.out.println(countAnagrams_Optimised("usjgmhcmhgdnmphnqkamhurktrffaclvgrzkkldacl",
                "lteojomonxrqyjzginrnnzwacxxaedrwudxzrfu"));
    }
}