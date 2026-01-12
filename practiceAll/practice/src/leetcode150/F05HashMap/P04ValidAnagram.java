package leetcode150.F05HashMap;

import java.util.HashMap;
import java.util.Map;

/*
Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
public class P04ValidAnagram {

    // using actual HashMap as map
    public static boolean validAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        for (char ch : t.toCharArray()) {
            if (freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) - 1);
                if (freqMap.get(ch).intValue() == 0) {
                    freqMap.remove(ch);
                }
            } else {
                freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            }
        }
        return freqMap.size() == 0;
    }

    // using array as freq map to map characters
    public static boolean validAnagramArray(String s, String t) {
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

    public static void main(String[] args) {
        System.out.println(validAnagram("anagram", "nagaram"));
        System.out.println(validAnagram("rat", "car"));
        System.out.println(validAnagramArray("anagram", "nagaram"));
        System.out.println(validAnagramArray("rat", "car"));
    }
}