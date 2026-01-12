package leetcode150.F03Slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class P12LongestRepeatingCharacterReplacement {

    public static int bruteforce(String s, int k) {
        // build all substring
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> map = new HashMap<>();
            int freq = 0;
            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                freq = Math.max(freq, map.get(ch));
                if ((j - i + 1) - freq <= k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
            map.clear();
        }
        return ans;
    }

    public static int longestCharacterReplacement(String s, int k) {
        int ans = 0;
        int i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxFreq = 0;
        while (j < s.length()) {
            // calculations
            char ch = s.charAt(j);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(ch));
            while ((j - i + 1) - maxFreq > k) {// targetted window.
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                i++;
            }
            // get ans from calc
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(bruteforce("AABABBA", 1));//
        System.out.println(longestCharacterReplacement("AABABBA", 1));//
    }
}