package leetcode150.F03Slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P10LongestSubstringWithKUniqueCharacters {

    public static int longestSubstrwithKUniqueChar_brute(String s, int k) {
        int ans = -1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            for (int j = i; j < s.length(); j++) {
                set.add(s.charAt(j));
                if (set.size() == k) {
                    ans = Math.max(ans, j - i + 1);
                }
                if (set.size() > k)
                    break;
            }
        }
        return ans;
    }

    public static int longestSubstrwithKUniqueChar(String s, int k) {
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        while (j < s.length()) {
            // calculations
            char ch = s.charAt(j);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.size() < k) {
                j++;
            } else if (map.size() == k) {
                // get ans from calculation
                longest = Math.max(longest, j - i + 1);
                j++;
            } else if (map.size() > k) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {
                    map.remove(s.charAt(i));
                }
                i++;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        System.out.println(longestSubstrwithKUniqueChar("aabacbebebe", 3));
    }
}