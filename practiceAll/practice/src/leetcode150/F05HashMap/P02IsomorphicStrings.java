package leetcode150.F05HashMap;

import java.util.HashMap;
import java.util.Map;

public class P02IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sTot = new HashMap<>();
        Map<Character, Character> tTos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            char ch2 = t.charAt(i);
            if ((sTot.containsKey(ch) && sTot.get(ch) != ch2) || (tTos.containsKey(ch2) && tTos.get(ch2) != ch)) {
                return false;
            }
            sTot.put(ch, ch2);
            tTos.put(ch2, ch);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));// true
        // System.out.println(isIsomorphic("aa", "aab"));// true
    }
}