package leetcode;

import java.util.Arrays;

public class ScrambledString {
    public static void main(String[] argv) {
        String[] words = { "baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz" };
        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbblkkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";
        ScrambledString sol = new ScrambledString();
        System.out.println(sol.scrambledString(words, note1));
        System.out.println(sol.scrambledString(words, note2));
        System.out.println(sol.scrambledString(words, note3));
        System.out.println(sol.scrambledString(words, note4));
        System.out.println(sol.scrambledString(words, note5));
        System.out.println(sol.scrambledString(words, note6));
        System.out.println(sol.scrambledString(words, note7));
    }

    public String scrambledString(String[] words, String str) {
        char ch[] = str.toCharArray();
        Arrays.sort(ch);
        for (String word : words) {
            char[]ch2 = word.toCharArray();
            Arrays.sort(ch2);
            if (compare(ch2, ch)) {
                return word;
            }
        }
        return "-";

    }
    public boolean compare(char[]arr1, char[]arr2){
        int i=0;
        int j=0;
        if(arr1.length>arr2.length){
            return false;
        }
        while(j<arr2.length){
            if(i<arr1.length && arr1[i]==arr2[j]){
                i++;
            }
            j++;
        }
        return i==arr1.length;
    }


}
