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
## Attempt2
```java
/*
You are analyzing data for Aquaintly, a hot new social network.

On Aquaintly, connections are always symmetrical. If a user Alice is connected to Bob, then Bob is also connected to Alice.

You are given a sequential log of CONNECT and DISCONNECT events of the following form:
- This event connects users Alice and Bob: ["CONNECT", "Alice", "Bob"]
- This event disconnects the same users: ["DISCONNECT", "Bob", "Alice"] (order of users does not matter)

We want to separate users based on their popularity (number of connections). To do this, write a function that takes in the event log and a number N and returns two collections:
[Users with fewer than N connections], [Users with N or more connections]

Example:
events = [
    ["CONNECT","Alice","Bob"],
    ["DISCONNECT","Bob","Alice"],
    ["CONNECT","Alice","Charlie"],
    ["CONNECT","Dennis","Bob"],
    ["CONNECT","Pam","Dennis"],
    ["DISCONNECT","Pam","Dennis"],
    ["CONNECT","Pam","Dennis"],
    ["CONNECT","Edward","Bob"],
    ["CONNECT","Dennis","Charlie"],
    ["CONNECT","Alice","Nicole"],
    ["CONNECT","Pam","Edward"],
    ["DISCONNECT","Dennis","Charlie"],
    ["CONNECT","Dennis","Edward"],
    ["CONNECT","Charlie","Bob"]
]

Using a target of 3 connections, the expected results are:
Users with less than 3 connections: ["Alice", "Charlie", "Pam", "Nicole"]
Users with 3 or more connections: ["Dennis", "Bob", "Edward"]

All test cases:
grouping(events, 3) => [["Alice", "Charlie", "Pam", "Nicole"], ["Dennis", "Bob", "Edward"]]
grouping(events, 1) => [[], ["Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"]]
grouping(events, 10) => [["Alice", "Charlie", "Dennis", "Bob", "Pam", "Edward", "Nicole"], []]
Complexity Variable:
E = number of events
Map<String,Set<connected ser>> alice, <charlie,Nicle>
                                bob , <> 
                                charlie <alice, nicle
*/  

import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Solution {
  
  public static List<List<String>> groupEvents(String[][]events, int N){
    List<List<String>> result = new ArrayList<>();
    Map<String, Set<String>> map = new LinkedHashMap<>();
    for(String[]event:events){//O(E)
      // ["CONNECT","Alice","Bob"],
      if(event[0].equals("CONNECT")){
        map.putIfAbsent(event[1],new HashSet<>());
        map.putIfAbsent(event[2],new HashSet<>());
        map.get(event[1]).add(event[2]);// check condition if valid
        map.get(event[2]).add(event[1]);
      }else if(event[0].equals("DISCONNECT")) {
        if(map.containsKey(event[1])){
          if(map.get(event[1]).contains(event[2])){
            map.get(event[1]).remove(event[2]);
          }
        }
        if(map.containsKey(event[2])){
          if(map.get(event[2]).contains(event[1])){
            map.get(event[2]).remove(event[1]);
          }
        }
      }
    }
    // O(M) - distinct users in arr 
    List<String> less = new ArrayList<>();
    List<String> more = new ArrayList<>();
    for(Map.Entry<String,Set<String>> entry: map.entrySet()){
      // System.out.println(entry.getKey());
      // System.out.println(entry.getValue());
      if(entry.getValue().size()<N){
        less.add(entry.getKey());
      }else{
        more.add(entry.getKey());
      }
    }
    result.add(less);
    result.add(more);
    return result;
  }
  
  public static void main(String[] argv) {
    String[][] events = {
      {"CONNECT","Alice","Bob"},
      {"DISCONNECT","Bob","Alice"},
      {"CONNECT","Alice","Charlie"},
      {"CONNECT","Dennis","Bob"},
      {"CONNECT","Pam","Dennis"},
      {"DISCONNECT","Pam","Dennis"},
      {"CONNECT","Pam","Dennis"},
      {"CONNECT","Edward","Bob"},
      {"CONNECT","Dennis","Charlie"},
      {"CONNECT","Alice","Nicole"},
      {"CONNECT","Pam","Edward"},
      {"DISCONNECT","Dennis","Charlie"},
      {"CONNECT","Dennis","Edward"},
      {"CONNECT","Charlie","Bob"}
    };
   List<List<String>> res =  groupEvents(events,3);
   for(List<String> r:res){
     System.out.println(r);
   }
   res =  groupEvents(events,1);
   for(List<String> r:res){
     System.out.println(r);
   }
   res =  groupEvents(events,10);
   for(List<String> r:res){
     System.out.println(r);
   }
  }
}
```
