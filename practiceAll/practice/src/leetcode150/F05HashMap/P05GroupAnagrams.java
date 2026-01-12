package leetcode150.F05HashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example 1:
 * 
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Explanation:
 * 
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form
 * each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to
 * form each other.
 * Example 2:
 * 
 * Input: strs = [""]
 * Output: [[""]]
 * 
 * Example 3:
 * 
 * Input: strs = ["a"]
 * Output: [["a"]]
 * 
 * Constraints:
 * 
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class P05GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        List<List<String>> res = groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });
        printlist(res);
    }
    
    public static void printlist(List<List<String>> res) {
        for (List<String> list : res) {
            System.out.println(list);
        }
    }

}