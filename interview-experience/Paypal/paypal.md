# Paypal karat interview question

platform is implementing A/B testing tool for putting thumbnail.
what is the issue here

-------------------

- capcaity estimation
3k user update 5 post per day with  2-10kb workday post avg 5kb,
no peak write
users read 10 post per day
estimating 100% capacity increase next year
how do we manage database and capacity estimation

---;

/*
You're creating a game with some amusing mini-games, and you've decided to make a simple variant of the game Mahjong.

In this variant, players have a number of tiles, each marked 0-9. The tiles can be grouped into pairs or triples of the same tile. For example, if a player has "33344466", the player's hand has a triple of 3s, a triple of 4s, and a pair of 6s. Similarly, "55555777" has a triple of 5s, a pair of 5s, and a triple of 7s.

A "complete hand" is defined as a collection of tiles where all the tiles can be grouped into any number of triples (zero or more) and exactly one pair, and each tile is used in exactly one triple or pair.

Write a function that takes a string representation of a collection of tiles in no particular order, and returns true or false depending on whether or not the collection represents a complete hand.
//check pairs -> but if there
[2,3]
tiles_1 = "88844"           # True. Base case - a pair and a triple

tiles_2 = "99"              # True. Just a pair is enough.
tiles_3 = "55555"           # True. The triple and a pair can be of the same tile value
tiles_4 = "22333333"        # True. A pair and two triples
tiles_5 = "73797439949499477339977777997394947947477993"
                            # True. 3*6%3, 4*8->2, 7*15, 9*15 - 4 have two triples and a pair, other numbers have just triples (multiple ones).
tiles_6 = "111333555"       # False. There are three triples, 111 333 555 but no pair
tiles_7 = "42"              # False. Two singles not forming a pair
tiles_8 = "888"             # False. A triple, no pair
tiles_9 = "100100000"       # False. A pair of 1 and two triples of 0, a leftover of 0
tiles_10 = "346664366"      # False. Three pairs and a triple
tiles_11 = "8999998999898"  # False. A triple of 8, three triples of 9, a leftover of 8
tiles_12 = "17610177"       # False. Triples of 1, and 7, leftovers of 6 and 0
tiles_13 = "600061166"      # False. A pair of 1, triple of 0, triple of 6, and a leftover of 6
tiles_14 = "6996999"        # False. A pair of 6, a triple of 9 and another pair of 9
tiles_15 = "03799449"       # False. A pair of 4, triple of 9 and 0, 3, and a leftover of 7
tiles_16 = "64444333355556" # False. A pair of 6, two pairs each of 3, 4, 5
tiles_17 = "7"              # False. No pairs and a leftover of 7
tiles_18 = "776655"         # False. Three pairs

complete(tiles_1) => True
complete(tiles_2) => True
complete(tiles_3) => True
complete(tiles_4) => True
complete(tiles_5) => True
complete(tiles_6) => False
complete(tiles_7) => False
complete(tiles_8) => False
complete(tiles_9) => False
complete(tiles_10) => False
complete(tiles_11) => False
complete(tiles_12) => False
complete(tiles_13) => False
complete(tiles_14) => False
complete(tiles_15) => False
complete(tiles_16) => False
complete(tiles_17) => False
complete(tiles_18) => False

Complexity Variable
N - Number of tiles in the input string
*/
//0 or more triple and exactly  1 pair

```java
import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Solution {
  public static void main(String[] argv) {
    String tiles_1 = "88844";
    String tiles_2 = "99";
    String tiles_3 = "55555";
    String tiles_4 = "22333333";
    String tiles_5 = "73797439949499477339977777997394947947477993";
    String tiles_6 = "111333555";
    String tiles_7 = "42";
    String tiles_8 = "888";
    String tiles_9 = "100100000";
    String tiles_10 = "346664366";
    String tiles_11 = "8999998999898";
    String tiles_12 = "17610177";
    String tiles_13 = "600061166";
    String tiles_14 = "6996999";
    String tiles_15 = "03799449";
    String tiles_16 = "64444333355556";
    String tiles_17 = "7";
    String tiles_18 = "776655";
    System.out.println(complete(tiles_1));
    System.out.println(complete(tiles_2));
    System.out.println(complete(tiles_3));
    System.out.println(complete(tiles_4));
    System.out.println(complete(tiles_5));

    System.out.println(complete(tiles_6));
    System.out.println(complete(tiles_7));
    System.out.println(complete(tiles_8));
    System.out.println(complete(tiles_9));
    System.out.println(complete(tiles_10));
    System.out.println(complete(tiles_11));
    System.out.println(complete(tiles_12));
    System.out.println(complete(tiles_13));
    System.out.println(complete(tiles_14));
    System.out.println(complete(tiles_15));
    System.out.println(complete(tiles_16));
    System.out.println(complete(tiles_17));
    System.out.println(complete(tiles_18));
  }
  
  public static boolean complete(String tiles){
    if(tiles==null || tiles.equals("")){
      return false;
    }
    int[]arr = new int[10];
    for(char c:tiles.toCharArray()){
      arr[c-'0'] = (arr[c-'0']+1)%3;
    }
    // System.out.println(tiles+" " +Arrays.toString(arr));
    int pair = 0,other=0;
    for(int i=0;i<=9;i++){
      if(arr[i]>0 && arr[i]%2!=0){
        other++;
      }else {
        if(arr[i]>0 && arr[i]%2==0){
          pair++;
        }
      }
    }
    // System.out.println(" pair "+pair+" other "+other);
    return pair==1 && other==0;
  }
}
```

-------------------

another round of paypal

design a chess game
R2 - define api contract and high/low level design

R1 - last stone weight question / with priority queue internal implementation
 puzzle for 25 horses and need to buy 3 horse
 min number of races required to buy 3 top horses

R3:
 PR code review understanding
 some LLd basic debugging and designing strategy

[high level design of transaction reversal system.](./paypalR3.excalidraw)
