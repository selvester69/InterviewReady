package leetcode150.F05HashMap;

import java.util.HashMap;
import java.util.Map;

public class P03WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length())
            return false;
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> revMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if ((map.containsKey(ch) && !map.get(ch).equals(words[i]))
                    || (revMap.containsKey(words[i]) && revMap.get(words[i]) != ch))
                return false;
            map.put(ch, words[i]);
            revMap.put(words[i], ch);
        }
        return true;
    }
}