package leetcode150.F07Stack;

import java.util.Stack;

public class P08RemoveKAdjacentDuplicate {
    class Pair {
        char ch;
        int freq;

        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String removeDuplicatesUsingStack(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().ch == ch) {
                stack.peek().freq++;
                if (stack.peek().freq == k) {
                    stack.pop();
                }
                continue;
            }
            stack.push(new Pair(ch, 1));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            while (p.freq-- > 0) {
                sb.insert(0, p.ch);
            }
        }
        return sb.toString();
    }

    public String removeDuplicatesUsingArrayAsStack(String s, int k) {
        Pair[] st = new Pair[s.length()];
        int ptr = -1;
        for (int i = 0; i < s.length(); i++) {
            if (ptr == -1 || st[ptr].ch != s.charAt(i)) {
                ptr++;
                st[ptr] = new Pair(s.charAt(i), 1);
            } else {
                st[ptr].freq++;
                if (st[ptr].freq == k) {
                    ptr--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= ptr; i++) {
            for (int j = 0; j < st[i].freq; j++) {
                sb.append(st[i].ch);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        P08RemoveKAdjacentDuplicate obj = new P08RemoveKAdjacentDuplicate();
        System.out.println(obj.removeDuplicatesUsingStack("deeedbbcccbdaa", 3));
    }
}
