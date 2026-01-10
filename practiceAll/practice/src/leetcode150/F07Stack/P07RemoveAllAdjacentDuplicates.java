package leetcode150.F07Stack;

import java.util.Stack;

//1047. Remove All Adjacent Duplicates In String
public class P07RemoveAllAdjacentDuplicates {

    public String removeDuplicatesUsingStack(String s) {
        Stack<Character> st = new Stack<>();
        for(char ch:s.toCharArray()){
            if(! st.isEmpty() &&st.peek()==ch){
                st.pop();
                continue;
            }
            st.push(ch);
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.insert(0, st.pop());
        }
        return sb.toString();
    }

    // using pointer as stack in arrray;
    public String removeDuplicates(String s) {
        char[] ch = s.toCharArray();
        int ptr = -1;
        for (int i = 0; i < ch.length; i++) {
            if (ptr == -1 || ch[ptr] != ch[i]) {
                ptr++;
                ch[ptr] = ch[i];
            } else {
                ptr--;
            }
        }
        return new String(ch, 0, ptr + 1);
    }
}
