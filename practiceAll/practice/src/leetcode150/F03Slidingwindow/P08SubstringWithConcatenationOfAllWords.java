package leetcode150.F03Slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P08SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int m = words.length;
        int w = words[0].length();
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        // loop i till total chars in each word
        for (int i = 0; i < w; i++) {
            Map<String, Integer> map = new HashMap<>();
            int j = i;
            for (int k = i; k + w <= n; k += w) {
                String currWord = s.substring(k, k + w);
                map.put(currWord, map.getOrDefault(currWord, 0) + 1);
                if (k - j + w == m * w) {// all words length are met, ans might be present
                    if (map.equals(freqMap)) {
                        res.add(j);
                    }
                    String removeWord = s.substring(j, j + w);
                    if (map.get(removeWord) > 1) {
                        map.put(removeWord, map.get(removeWord) - 1);
                    } else {
                        map.remove(removeWord);
                    }
                    j += w;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = findSubstring("barfoothefoobarman", new String[] { "foo", "bar" });
        System.out.println(res);
    }
}