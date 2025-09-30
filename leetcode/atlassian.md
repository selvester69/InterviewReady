# Atlassian

## Attempt 1

```java
import java.io.*;
import java.util.*;
import javafx.util.Pair;
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
public class Solution {
  
  public static Map<Character,Integer> getFreq(String message){
    Map<Character,Integer> freq = new HashMap<>();
    for(char ch:message.toCharArray()){
      freq.put(ch, freq.getOrDefault(ch,0)+1);
    }
    return freq;
  }
  public static boolean isValid(String word, Map<Character,Integer> freqMap){
    int[]freq = new int[26];
    for(char ch:word.toCharArray()){
      freq[ch-'a']++;
    }
    // System.out.println(Arrays.toString(freq));
    for(int i=0;i<26;i++){
      if(freq[i]>0){// for char in word list
        char key = (char) ('a'+i);

        if(!freqMap.containsKey(key) || freq[i]>freqMap.get(key)){
          return false;
        }
      }
    }

    return true;
  }
  public static String scrambledString(String[]words, String message){
      Map<Character,Integer> freqMap = getFreq(message);
      // System.out.println(freqMap);
      for(String word:words){
        if(isValid(word,freqMap)){
          return word;
        }
      }
      return "-";
  }
  public static void main(String[] argv) {
    System.out.println("Hello, world!");
    System.out.println("This is a fully functioning Java environment.");
    String[]words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
     String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbblkkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";
        System.out.println(scrambledString(words, note1));
        System.out.println(scrambledString(words, note2));
        System.out.println(scrambledString(words, note3));
        System.out.println(scrambledString(words, note4));
        System.out.println(scrambledString(words, note5));
        System.out.println(scrambledString(words, note6));
        System.out.println(scrambledString(words, note7));
  }
}

```
