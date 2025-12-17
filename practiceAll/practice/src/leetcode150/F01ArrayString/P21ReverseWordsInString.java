package leetcode150.F01ArrayString;

public class P21ReverseWordsInString {

    public String reverseWordsInString(String s) {
        char ch[] = s.toCharArray();
        reverse(ch, 0, ch.length - 1);
        int i = 0;
        for (int j = 0; j < ch.length; j++) {
            if (ch[i] == ' ') {
                i++;
                continue;
            }
            while (j < ch.length && ch[j] != ' ') {
                j++;
            }
            reverse(ch, i, j-1);
            i=j;
        }
        return new String(ch);
    }

    public void reverse(char ch[], int start, int end) {
        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        P21ReverseWordsInString obj = new P21ReverseWordsInString();
        System.out.println(obj.reverseWordsInString("    This is a Test String.    "));
        System.out.println(obj.reverseWordsInString("Hello"));
        System.out.println(obj.reverseWordsInString("   abc.  aaa"));
    }

}
