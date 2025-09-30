```java
/*
You are running a classroom and suspect that some of your students are passing around the answer to a multiple-choice question disguised as a random note.

Your task is to write a function that, given a list of words and a note, finds and returns the word in the list that is scrambled inside the note, if any exists. If none exist, it returns the result "-" as a string. There will be at most one matching word. The letters don't need to be in order or next to each other. The letters cannot be reused.

Example:  
words = ["baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"]
note1 = "ctay"
find(words, note1) => "cat"   (the letters do not have to be in order)  
  
note2 = "bcanihjsrrrferet"
find(words, note2) => "cat"   (the letters do not have to be together)  
  
note3 = "tbaykkjlga"
find(words, note3) => "-"     (the letters cannot be reused)  
  
note4 = "bbbblkkjbaby"
find(words, note4) => "baby"    
  
note5 = "dad"
find(words, note5) => "-"    
  
note6 = "breadmaking"
find(words, note6) => "bird"    

note7 = "dadaa"
find(words, note7) => "dada"    

All Test Cases:
find(words, note1) -> "cat"
find(words, note2) -> "cat"
find(words, note3) -> "-"
find(words, note4) -> "baby"
find(words, note5) -> "-"
find(words, note6) -> "bird"
find(words, note7) -> "dada"
  
Complexity analysis variables:  
  
W = number of words in `words`  
S = maximal length of each word or of the note  
*/
import java.io.*;
import java.util.*;

import javafx.util.Pair;

public class Solution {
  public static void main(String[] argv) {
    String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
    String note1 = "ctay";
    String note2 = "bcanihjsrrrferet";
    String note3 = "tbaykkjlga";
    String note4 = "bbbblkkjbaby";
    String note5 = "dad";
    String note6 = "breadmaking";
    String note7 = "dadaa";
    Solution sol = new Solution();
   String ans =  sol.scrambledString(words,note1);
   System.out.println(ans);
  }
  public String scrambledString(String[]words, String str){
    //freqMap is ready 
    int[]strArray = new int[26];
    //create new freq of string
    for(char c:str.toCharArray()){
      strArray[c-'a']++;
    }
    // Map<String, Integer[]> freqMap = new HashMap<>();
    for(String word:words){
      int[]ch = new int[26];
      for(char c:word.toCharArray()){
        ch[c-'a']++;
      }
      if(compareFrequency(strArray,ch)){// we need to pass scrambled word char as first params
       return word;
      }
      // freqMap.put(word,ch);
      
    }

    return "-";

  }
  /*
  cbtay
  1110,,,1,00,1,0
  cat
  1,0,1,..1,000
  */
  public boolean compareFrequency(int[] arr1, int[]arr2){
    for(int i=0;i<26;i++){
      // for any case arr[i]== < than arr2[i]
      if(arr1[i]> arr2[i]){//not correct 
        return false;
      }
    }
    return true;
  }
  
}
```
