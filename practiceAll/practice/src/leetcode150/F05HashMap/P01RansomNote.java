package leetcode150.F05HashMap;

import java.util.HashMap;
import java.util.Map;

public class P01RansomNote {
    // using 2 maps and comparing

    public static boolean ransomNote(String ransom, String magazine) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        Map<Character, Integer> ransomMap = new HashMap<>();
        for (char ch : ransom.toCharArray()) {
            ransomMap.put(ch, ransomMap.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : ransomMap.entrySet()) {
            char key = entry.getKey();
            if (!freq.containsKey(key) || entry.getValue().intValue() > freq.get(key).intValue()) {
                return false;
            }
        }
        return true;
    }

    // using 1 map
    public static boolean ransomNote_Single_Map(String ransom, String magazine) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        for (char c : ransom.toCharArray()) {
            if (freq.getOrDefault(c, 0) > 0) {
                freq.put(c, freq.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    // Optimized
    // using char array as freq map
    public static boolean ransomNote_Optimized(String ransom, String magazine) {
        int[] freq = new int[26];
        for (char ch : magazine.toCharArray()) {
            freq[ch - 'a']++;
        }
        for (char ch : ransom.toCharArray()) {
            freq[ch - 'a']--;
            if (freq[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ransomNote("a", "b"));// false
        System.out.println(ransomNote("aa", "aab"));// true

        System.out.println(ransomNote_Optimized("a", "b"));// false
        System.out.println(ransomNote_Optimized("aa", "aab"));// true
    }
}