package leetcode150.F03Slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P13EncodeAndDecodeString {

    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append("#").append(s);
        }
        return sb.toString();
    }

    // applying window to find string
    public static List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            j = i + length;
            res.add(str.substring(i, j));
            i = j;
        }
        return res;
    }

    public static void main(String[] args) {
        String enc = encode(Arrays.asList("Hello", "World"));
        System.out.println(enc);
        List<String> res = decode(enc);
        System.out.println(res);
    }
}