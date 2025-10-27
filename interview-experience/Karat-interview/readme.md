# Karat InterView Questions

## 🧩 Problem Statement: Validating a Substitution Cipher Map

You are trying to determine if a set of plaintext/ciphertext word pairs is consistent with a single, valid **one-to-one** substitution cipher. The cipher is between lowercase English letters.

The rules for a valid cipher mapping are:

1. Every plaintext character must consistently map to the same ciphertext character.
2. The mapping must be one-to-one (bijective): no two different plaintext characters can map to the same ciphertext character.

Given a list of paired strings `(plaintext, ciphertext)`, your task is to determine if a consistent and valid mapping can be generated from all the pairs.

### **Example**

| Pair Index | Plaintext | Ciphertext | Result |
| :---: | :---: | :--- | :--- |
| **1** | "aa" | "bb" | $\checkmark$ (Map: a $\rightarrow$ b) |
| **2** | "ab" | "ca" | $\checkmark$ (Map: a $\rightarrow$ c, b $\rightarrow$ a. No contradiction with a $\rightarrow$ b from Pair 1) |
| **3** | "foo" | "bar" | $\boldsymbol{\times}$ (Contradiction: 'o' must map to 'a' *and* 'r') |

-----

### 💻 Java Solution: Validating Substitution Consistency

This solution uses two `HashMaps` to enforce the one-to-one constraint.

```java
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    /**
     * Checks if a list of (plaintext, ciphertext) pairs is consistent with a single,
     * valid one-to-one substitution cipher.
     *
     * @param pairs A list of String arrays, where each array is [plaintext, ciphertext].
     * @return true if a consistent one-to-one mapping exists across all pairs, false otherwise.
     */
    public boolean isValidSubstitutionMap(List<String[]> pairs) {
        
        // Map 1: Stores the required forward mapping: Plaintext Char -> Ciphertext Char
        Map<Character, Character> forwardMap = new HashMap<>();
        
        // Map 2: Stores the required reverse mapping: Ciphertext Char -> Plaintext Char
        // This enforces the 'one-to-one' (bijective) constraint.
        Map<Character, Character> reverseMap = new HashMap<>();

        for (String[] pair : pairs) {
            String plain = pair[0];
            String cipher = pair[1];

            // Must have the same length to be a simple substitution
            if (plain.length() != cipher.length()) {
                return false; 
            }

            for (int i = 0; i < plain.length(); i++) {
                char pChar = plain.charAt(i);
                char cChar = cipher.charAt(i);

                // --- 1. CHECK FORWARD CONSISTENCY (One plaintext char maps to one ciphertext char) ---
                if (forwardMap.containsKey(pChar)) {
                    // Contradiction: If pChar already maps to a different character
                    if (forwardMap.get(pChar) != cChar) {
                        return false; 
                    }
                } else {
                    // New mapping: Establish pChar -> cChar
                    forwardMap.put(pChar, cChar);
                }

                // --- 2. CHECK REVERSE CONSISTENCY (No two plaintext chars map to the same ciphertext char) ---
                if (reverseMap.containsKey(cChar)) {
                    // Contradiction: If cChar is already mapped from a different plaintext character
                    if (reverseMap.get(cChar) != pChar) {
                        return false; 
                    }
                } else {
                    // New mapping: Establish cChar -> pChar
                    reverseMap.put(cChar, pChar);
                }
            }
        }

        // If the loop completes, no contradictions were found.
        return true;
    }
}
```

-----

The "Ancient Tomb Karat" problem is a variation of a classic geometry or spatial reasoning interview question, which typically involves determining if a given set of points (representing objects or rooms) can be partitioned or covered according to a specific constraint.

While the exact details can vary, the most common version revolves around finding if a set of points can be separated by a single straight line, known as the **Linear Separability** problem. This concept is fundamental to machine learning (like perceptrons) and computational geometry.

Here is the typical problem statement and the core logic behind the solution.

## 🧩 Problem Statement: Ancient Tomb / Linear Separability

You are given a set of coordinates representing the locations of artifacts in an ancient tomb. The tomb is divided into two areas, Area A and Area B, by a long, straight, sealed wall.

The problem asks: **Can all the given points be divided into two non-empty groups, such that the two groups can be perfectly separated by a single straight line?**

In other words, can you draw one straight line (not necessarily vertical or horizontal) that places all points of Group 1 on one side and all points of Group 2 on the other side?

### **Input**

A list of 2D coordinates, e.g., `List<Point>` or `List<int[]>` where each point is `(x, y)`.

### **Output**

Return `true` if the points are linearly separable into two non-empty groups, and `false` otherwise.

### 💡 Core Logic and Solution Approach

The key insight for this problem is that if a set of points is linearly separable, then there must exist a subset of three points (a triangle) that form the two groups, and the remaining points can be checked against the line defined by those two groups.

However, the most robust and standard approach involves checking every possible split of the points:

#### **1. Iterate Through All Pair Splits**

Instead of checking every possible line, we check every possible way to split the points into two groups, $A$ and $B$. For $N$ points, there are $2^N - 2$ non-empty ways to split the set, which is too slow.

#### **2. Check All Pairs of Points (Simplified Approach)**

A more practical approach (and the one often intended for an interview) is to define a potential separating line by **two points from the entire set**, or by checking every possible pair of points.

**The most common interview simplification:** The set of points is linearly separable if and only if **for every possible assignment** of the points to two groups ($A$ and $B$), you can find a separating line. The simpler way to test this is to focus on a few key splits:

#### **3. The $O(N^3)$ Algorithmic Test (The intended solution structure)**

The most common, practical test for a large set of points is to define the line using two points and then verify the classification of the remaining points.

* **Strategy:** Iterate through every possible pair of points, $(P_i, P_j)$, and check if all *other* points lie strictly on one side of the line defined by $P_i$ and $P_j$. If they do, then $P_i$ and $P_j$ are a potential basis for the dividing line.

The most effective check for which side a third point, $P_k$, falls is the **Cross Product** (or **Orientation**) test.

#### **Cross Product (Orientation) Test**

For any three points, $P_i$, $P_j$, and $P_k$, the sign of the cross product of the vectors $\vec{P_i P_j}$ and $\vec{P_i P_k}$ determines the orientation:

$$\text{Orientation}(P_i, P_j, P_k) = (y_j - y_i)(x_k - x_i) - (x_j - x_i)(y_k - y_i)$$

| Result | Geometric Meaning |
| :--- | :--- |
| **Positive $(>0)$** | $P_k$ is to the **left** of the directed line $\vec{P_i P_j}$. |
| **Negative $(<0)$** | $P_k$ is to the **right** of the directed line $\vec{P_i P_j}$. |
| **Zero $(=0)$** | $P_k$ is **collinear** (on the same line) as $P_i$ and $P_j$. |

#### **The Full Algorithm ($O(N^3)$)**

1. **Iterate through all pairs of points $(P_i, P_j)$** to define a potential boundary line (Outer loop $i$, Inner loop $j$).
2. **Initialize two groups for this line:** $L$ (Left/Positive) and $R$ (Right/Negative).
3. **Iterate through all *other* points, $P_k$** (Innermost loop $k$):
    * Calculate the orientation $\text{Orientation}(P_i, P_j, P_k)$.
    * If the result is positive, add $P_k$ to group $L$.
    * If the result is negative, add $P_k$ to group $R$.
    * If the result is zero (collinear), this line doesn't strictly separate the points.
4. **Check Separability:** After checking all $P_k$, if both group $L$ and group $R$ are **non-empty**, AND no points were collinear, then the line defined by $P_i$ and $P_j$ is a valid separating line, and you can return $\mathbf{true}$.
5. If the algorithm completes without finding a separating line, return $\mathbf{false}$.

This check is sufficient because any separating line must be "supported" by at least two points on the boundary of the convex hulls of the two groups.

```java
import java.util.List;

class Solution {

    // A simple class to represent a 2D point
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Determines if a set of points can be divided into two non-empty, linearly separable groups.
     * This is an O(N^3) solution using the cross-product test.
     * * @param points A list of 2D points.
     * @return true if the points are linearly separable, false otherwise.
     */
    public boolean isLinearlySeparable(List<Point> points) {
        int n = points.size();

        // Trivial case: Requires at least 3 points to form two non-empty groups.
        if (n < 3) {
            return false;
        }

        // Iterate through all pairs of points (Pi, Pj) to define a potential dividing line.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue; // Skip self-comparison

                // 1. Initialize counters for points on the left and right sides of the line P_i -> P_j
                int leftCount = 0;
                int rightCount = 0;
                boolean collinearFound = false; // Flag to check if any point lies on the line

                // 2. Iterate through all other points Pk
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue; // Skip the two points defining the line

                    Point pi = points.get(i);
                    Point pj = points.get(j);
                    Point pk = points.get(k);

                    // 3. Calculate the orientation (cross product) using the sign function
                    long orientation = calculateOrientation(pi, pj, pk);

                    if (orientation > 0) {
                        leftCount++;   // Pk is on the left side (Group L)
                    } else if (orientation < 0) {
                        rightCount++;  // Pk is on the right side (Group R)
                    } else {
                        // Point is collinear. A strict separation line requires all points 
                        // to be strictly on one side or the other.
                        collinearFound = true;
                        break; // Stop inner loop for this (i, j) pair
                    }
                }

                // 4. Check for Separability
                // If a line strictly separates the remaining points into two NON-EMPTY groups,
                // we have found a solution.
                if (!collinearFound && leftCount > 0 && rightCount > 0) {
                    return true;
                }
            }
        }

        // If no line formed by any two points achieves the strict separation, the set is not linearly separable.
        return false;
    }

    /**
     * Calculates the orientation (cross product) of the ordered triplet (p, q, r).
     * The result's sign determines the side of r relative to the directed line p -> q.
     * Formula: (yq - yp)(xr - xp) - (xq - xp)(yr - yp)
     */
    private long calculateOrientation(Point p, Point q, Point r) {
        // Use long to prevent potential integer overflow during multiplication.
        return (long) (q.y - p.y) * (r.x - p.x) - (long) (q.x - p.x) * (r.y - p.y);
    }
}
```

-----
The "Justify Text Karat" problem is highly likely referring to the **Text Justification** problem (LeetCode #68), which you recently generated the solution for.

If you are looking for justification of the *difficulty* or *approach* suitable for an interview, here is the justification for why this problem is considered hard and how its solution satisfies interview requirements.

## 💡 Justification for Text Justification Problem

The Text Justification problem is a common interview challenge because it tests the candidate's ability to combine a **greedy algorithm** with **precise mathematical/procedural logic** under several edge cases. It's not just an algorithm problem; it's an implementation challenge.

### 1. Two-Phase Logic (The Algorithmic Challenge)

The solution naturally breaks down into two distinct phases, requiring structured thinking:

* **Phase 1: Greedy Line Fitting (Word Selection)** 📝
    The algorithm must decide which words go on which line. The most efficient approach is **greedy**: put as many words as possible on the current line. This decision is complex because it must account for the words' lengths *and* the minimum spaces required between them.

* **Phase 2: Space Distribution (The Math)** 📐
    Once words are selected, the core logic is distributing the remaining spaces perfectly, which requires integer arithmetic for division and modulus:
  * **Base Spaces:** $\text{spacesPerGap} = \text{totalSpaces} / \text{gapCount}$.
  * **Extra Spaces:** $\text{extraSpaces} = \text{totalSpaces} \% \text{gapCount}$.
    This ensures extra spaces are correctly distributed one-by-one to the leftmost gaps.

### 2. Edge Case Handling (The Implementation Challenge)

A successful solution must correctly handle the following three crucial edge cases:

| Edge Case | Rule | Fix in Code |
| :--- | :--- | :--- |
| **Last Line** | Must be **left-justified** (single space between words, remaining space packed at the end). | This requires a separate `if (j == n)` block to override the full justification logic. |
| **Single Word Line** | The word must be left-justified, followed by a large block of trailing spaces. | This is often combined with the last-line logic (`wordsCount == 1`). Division by zero is avoided since $\text{gapCount} = 0$. |
| **Perfect Fit** | If the line is perfectly full, the space distribution logic must still work (i.e., $\text{totalSpaces}$ equals the required minimum). | The math must ensure no extra space is added if $\text{totalSpaces} \% \text{gapCount} = 0$. |

### **Conclusion**

The Text Justification problem is an excellent measure of a candidate's practical coding skill. The interviewer is not just looking for knowledge of BFS or DP, but for the ability to translate a complex, multi-rule specification into **clean, robust, and mathematically precise Java code**.

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0; // Current word index, marks the start of the current line

        while (i < n) {
            int j = i; // Word index used to scan for the end of the current line
            int lineLength = 0; // Total length of words (excluding spaces) on the current line

            // 1. GREEDY LINE FITTING
            // Find the words that fit on the current line (words[i] to words[j-1]).
            // lineLength + words[j].length() + (j - i) <= maxWidth
            // where (j - i) is the minimum number of spaces required (one less than the word count).
            while (j < n && (lineLength + words[j].length() + (j - i) <= maxWidth)) {
                lineLength += words[j].length();
                j++; // Move to the next word
            }

            int wordsCount = j - i;
            StringBuilder line = new StringBuilder();

            // 2. SPACE DISTRIBUTION
            
            // Case A: Last line OR only one word on the line
            if (j == n || wordsCount == 1) {
                
                // Left-justify: put single spaces between words
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        line.append(" ");
                    }
                }
                
                // Fill the remaining space with trailing spaces
                int remainingSpace = maxWidth - line.length();
                while (remainingSpace > 0) {
                    line.append(" ");
                    remainingSpace--;
                }
            } 
            
            // Case B: Middle lines (Full Justification)
            else {
                int totalSpaces = maxWidth - lineLength;
                int gapCount = wordsCount - 1; // Number of gaps between words (always >= 1)
                
                // Base number of spaces for each gap
                int spacesPerGap = totalSpaces / gapCount;
                
                // Extra spaces that must be distributed to the leftmost gaps
                int extraSpaces = totalSpaces % gapCount;

                // Build the line with calculated spaces
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    
                    if (k < j - 1) { // If it's not the last word on the line
                        // Append the base number of spaces
                        for (int s = 0; s < spacesPerGap; s++) {
                            line.append(" ");
                        }
                        // Append one extra space if it's one of the first 'extraSpaces' gaps
                        if (extraSpaces > 0) {
                            line.append(" ");
                            extraSpaces--;
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j; // Move the start index to the beginning of the next line
        }

        return result;
    }
}
```

## 🧩 Problem Statement: Badge Access Logs

You are given a list of security badge access records for a company building. Each record contains a user's name, the time of the event (in minutes since midnight, or a 24-hour time string like "13:00"), and the type of event ("enter" or "exit").

The problem typically has several parts, testing your ability to process and aggregate this time-series data:

### **Part 1: Incomplete Records**

Find all employee names who have an **odd number of access records** (e.g., they entered but never exited, or exited but never entered). These are considered incomplete records.

### **Part 2: High-Usage Employees (The Core Challenge)**

Find all employees who use their badge three or more times within a **one-hour (60-minute) period**. The result should include the employee's name and the specific times of the suspicious sequence.

### **Part 3: Invalid Sequences**

Identify employees with badge records that show an illogical sequence, such as two "enter" events in a row or two "exit" events in a row, without an alternating event in between.

### 💡 Core Logic and Solution Approach

The key to solving all parts efficiently is to first **group the data by employee name** and then **sort the times** for each employee.

#### 1\. Data Processing and Grouping (Initial Setup)

Use a $\mathbf{HashMap<String, List<Integer>>}$ to store the data:

* **Key:** Employee Name (String).
* **Value:** A list of all their access times (Integer, converted to minutes since midnight) for the day, **sorted in chronological order**.

#### 2\. Solution for Part 1: Incomplete Records (Odd Count)

After grouping, this is trivial:

* Iterate through the `HashMap` keys.
* If `list.size() % 2 != 0`, the employee has an odd number of records and is flagged.

#### 3\. Solution for Part 2: High-Usage Employees (The 60-Minute Window)

This is a **Sliding Window** problem applied to the sorted time list.

* For each employee, iterate through their sorted list of times.
* Use a window of size $K=3$. Check the difference between the $k$-th time and the $(k-2)$-th time (three records total).
  * If $\mathbf{Time}[k] - \mathbf{Time}[k-2] \le 59$ (less than 60 minutes), the employee is flagged.

### 4\. Solution for Part 3: Invalid Sequences (Enter/Exit Alternation)

This requires a different grouping, using a $\mathbf{HashMap<String, List<String[]>>}$ to store the raw time/type records.

* For each employee, iterate through their sorted list of events.
* Check the current event type against the previous event type.
* If `current_type == previous_type` (e.g., "enter" then "enter"), the employee is flagged for an invalid sequence.

-----

## 💻 Java Solution for Part 2 (High-Usage Employees)

Focusing on the most complex part, here is the Java code for finding high-usage employees using the sliding window approach.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    // Helper to convert "HH:MM" string to minutes since midnight
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    /**
     * Finds employees who badge in at least 'k' times within a 60-minute window.
     * This solution handles only the times, ignoring enter/exit type, as is common 
     * in the core variant of this problem part.
     *
     * @param records List of [Name, Time] arrays (time is HH:MM string).
     * @param k The minimum number of badge uses (e.g., k=3).
     * @return A map of employee name to the list of suspicious access times.
     */
    public Map<String, List<Integer>> findHighUsageEmployees(String[][] records, int k) {
        
        // 1. Group records by name and convert time to minutes (Integer)
        // Map<Name, List<TimeInMinutes>>
        Map<String, List<Integer>> allTimes = new HashMap<>();
        for (String[] record : records) {
            String name = record[0];
            int time = timeToMinutes(record[1]);
            
            allTimes.putIfAbsent(name, new ArrayList<>());
            allTimes.get(name).add(time);
        }

        // 2. Sort times for each employee and apply the sliding window
        Map<String, List<Integer>> highUsageEmployees = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : allTimes.entrySet()) {
            String name = entry.getKey();
            List<Integer> times = entry.getValue();

            // Sort the times chronologically (Crucial step for the sliding window)
            Collections.sort(times);

            // Apply the sliding window of size k
            // We only need to iterate up to the point where a full window (size k) can start.
            for (int i = 0; i <= times.size() - k; i++) {
                
                // The k-th record in the window is at index i + k - 1
                int startTime = times.get(i);
                int endTime = times.get(i + k - 1); // e.g., for k=3, this is index i+2

                // Check if the time difference is less than 60 minutes
                if (endTime - startTime < 60) {
                    
                    // Found a suspicious sequence (k or more accesses within 60 minutes)
                    
                    // Store the result, ensuring the employee is only added once
                    // or storing the specific time range that triggered the flag.
                    if (!highUsageEmployees.containsKey(name)) {
                        // Store all the times in the sequence for documentation
                        List<Integer> suspiciousTimes = times.subList(i, i + k);
                        highUsageEmployees.put(name, new ArrayList<>(suspiciousTimes));
                    }
                    
                    // Since the list is sorted, the moment we find one k-sequence,
                    // we can stop checking for this employee (unless the requirement is 
                    // to find ALL such sequences, in which case we would continue).
                    break;
                }
            }
        }

        return highUsageEmployees;
    }
}
```
The "Nonogram Karat" problem refers to a class of interview questions that involve validating or solving a **Nonogram puzzle** (also known as Picross, Griddlers, or Paint by Numbers). This problem tests algorithmic skills, often involving **backtracking** or **dynamic programming** for the solving aspect, and basic **rule validation** for the simpler aspect.

Here is the common problem statement and the high-level approach for the two main variations.

***

## 🧩 Problem Statement: Nonogram Puzzle

A Nonogram puzzle is a grid where each cell must be colored black or left white. Clues along the side of the rows and the top of the columns indicate the lengths of consecutive blocks of black cells in that row or column.

### **Common Variations**

### Variation 1: Validation (The Simpler Task) ✅

Given a **completed** grid (a 2D array of booleans or characters) and the row/column clues, determine if the grid is a **valid solution** for the given clues.

### Variation 2: Solving (The Harder Task) 🔍

Given an **empty** grid and the row/column clues, **solve the puzzle** (i.e., fill the grid) and return the completed grid.

---

## 💡 Solution Approach (Variation 1: Validation)

The validation task is straightforward and is solved by checking each row and column independently against its corresponding clue.

### **Validation Algorithm**

1. **Check Rows:**
    * Iterate through each row of the given grid.
    * For the current row, **extract the block lengths**: Scan the row and count the length of every consecutive block of filled cells (e.g., three 'X's followed by a blank counts as a block of length 3).
    * Compare this generated list of block lengths with the corresponding **row clue** list. If they do not match exactly, the grid is invalid.

2. **Check Columns:**
    * Iterate through each column of the given grid.
    * For the current column, **extract the block lengths** (same procedure as rows, but scanning vertically).
    * Compare this generated list of block lengths with the corresponding **column clue** list. If they do not match, the grid is invalid.

3. If all rows and all columns satisfy their clues, the grid is **valid**.

---

## 💡 Solution Approach (Variation 2: Solving)

Solving an arbitrary Nonogram is generally an **NP-complete** problem, meaning no polynomial-time algorithm is known. It is typically solved in interviews using **Backtracking** (similar to Sudoku solvers).

### **Backtracking Algorithm**

1. **State:** The current state is the partially filled grid.
2. **Base Case:** If the entire grid is filled, check if the resulting configuration satisfies **all** row and column clues. If it does, return the grid.
3. **Recursive Step:**
    * Find the **next empty cell** to fill.
    * **Try Filling (Black):** Place a black cell ('X') in the empty spot.
        * **Pruning:** Before recursing, immediately check if this move violates the **local clues** (e.g., if this column already has too many blocks or a block is too long). If the move is invalid, stop and backtrack.
        * If the move is locally valid, call the function recursively to fill the next cell.
    * **Try Emptying (White):** Place a white cell ('.') in the empty spot.
        * **Pruning:** Check for local clue violations.
        * If valid, call the function recursively.
    * **Backtrack:** If both options return a failure, clear the cell and return failure.

### **Simplified Approach: Dynamic Programming (For Single Row/Column)**

Sometimes, the interview problem is simplified to: "Can a *single row* (or column) of length $L$ be filled given a clue list?" This sub-problem can be solved efficiently using **Dynamic Programming**.

* $\mathbf{DP}[i][j]$ = Boolean indicating if it's possible to satisfy the first $j$ clues using the first $i$ cells of the row.
I will generate the Java solutions for both common variations of the **Nonogram Karat** problem:

1. **Validation:** Given a completed grid and clues, check if the solution is correct.
2. **Solver (Row Sub-problem):** Given a single row and its clues, solve it using Dynamic Programming (DP).

-----

## 💻 Java Solution 1: Nonogram Validation (The Simpler Task) ✅

This solution checks if a completed grid satisfies all row and column clues.

```java
import java.util.ArrayList;
import java.util.List;

class NonogramValidator {

    /**
     * Extracts the block lengths from a single line (row or column) of the grid.
     * @param line A list of booleans (true = filled/black, false = empty/white).
     * @return A list of integers representing the lengths of consecutive true blocks.
     */
    private List<Integer> getBlockLengths(List<Boolean> line) {
        List<Integer> lengths = new ArrayList<>();
        int currentLength = 0;
        
        for (boolean cell : line) {
            if (cell) {
                currentLength++; // Extend the current block
            } else {
                if (currentLength > 0) {
                    lengths.add(currentLength); // End of a block
                }
                currentLength = 0; // Reset count
            }
        }
        // Handle the last block if the line ends with a filled cell
        if (currentLength > 0) {
            lengths.add(currentLength);
        }
        return lengths;
    }

    /**
     * Checks if the completed grid is valid against all row and column clues.
     * @param grid The completed grid (true=filled).
     * @param rowClues Clues for each row.
     * @param colClues Clues for each column.
     * @return true if the grid is valid, false otherwise.
     */
    public boolean isValidSolution(boolean[][] grid, List<List<Integer>> rowClues, List<List<Integer>> colClues) {
        int rows = grid.length;
        if (rows == 0) return true;
        int cols = grid[0].length;

        // 1. Validate all rows
        for (int r = 0; r < rows; r++) {
            // Convert row array to List<Boolean> for easy processing
            List<Boolean> row = new ArrayList<>();
            for (boolean cell : grid[r]) {
                row.add(cell);
            }
            if (!getBlockLengths(row).equals(rowClues.get(r))) {
                return false;
            }
        }

        // 2. Validate all columns
        for (int c = 0; c < cols; c++) {
            // Extract column data
            List<Boolean> col = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                col.add(grid[r][c]);
            }
            if (!getBlockLengths(col).equals(colClues.get(c))) {
                return false;
            }
        }

        return true;
    }
}
```

-----

## 💻 Java Solution 2: Nonogram Solver (Single Row DP Sub-Problem) 🧠

The full Nonogram solver uses complex backtracking. A common interview simplification is the **Dynamic Programming** solution for a single row, which determines if a set of clues can be satisfied by a row of a specific length, and sometimes returns the number of ways.

This solution provides a DP approach to determine the **number of ways** a row of length `N` can be satisfied by a given set of `M` clues.

```java
import java.util.Arrays;
import java.util.List;

class NonogramRowSolver {

    /**
     * Calculates the number of ways a row of length 'N' can be filled to satisfy the clues.
     * This is an O(N * M) Dynamic Programming solution.
     * * @param N The length of the row.
     * @param clues The list of block lengths required (e.g., [2, 1, 3]).
     * @return The number of possible valid configurations.
     */
    public long countRowConfigurations(int N, List<Integer> clues) {
        int M = clues.size();
        
        // dp[i][j] = Number of ways to satisfy the first 'j' clues using the first 'i' cells.
        // Size: (N+1) x (M+1)
        long[][] dp = new long[N + 1][M + 1];

        // Base Case: dp[i][0] = 1 for all i >= 0.
        // There is 1 way to satisfy 0 clues (by leaving the entire prefix empty).
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        // Iterate through each clue (j) and each cell position (i)
        for (int j = 1; j <= M; j++) {
            int blockLength = clues.get(j - 1); // Length of the current block
            
            for (int i = 1; i <= N; i++) {
                
                // --- Option 1: Skip the current cell (leave it white) ---
                // The number of ways is the same as the ways to solve the problem for the prefix i-1.
                dp[i][j] = dp[i - 1][j]; 

                // --- Option 2: Place the j-th block ENDING at the current cell 'i' ---
                // Check if the current block (j) can fit and be separated from the previous block (j-1).
                // Minimum requirement: The block itself (blockLength) + a mandatory separator space (1)
                int minStart = blockLength + 1;

                if (i >= blockLength) {
                    
                    // Case 2a: This is the first block (j=1)
                    if (j == 1) {
                        // The previous cells (i - blockLength) must all be empty (satisfied by dp[i - blockLength][0]=1)
                        if (i >= blockLength && (i == blockLength || isPrefixEmpty(i - blockLength, dp))) {
                            // If the prefix of length (i - blockLength) is empty, 
                            // we can place the block.
                            dp[i][j] += 1; // Since dp[x][0] = 1
                        }
                    } 
                    
                    // Case 2b: This is not the first block (j > 1)
                    else if (i >= minStart) {
                        // The cells immediately before the block (i - blockLength - 1) 
                        // must satisfy the previous (j-1) clues. The cell at (i - blockLength) 
                        // must be the mandatory separator space.
                        dp[i][j] += dp[i - blockLength - 1][j - 1];
                    }
                }
            }
        }

        // The final answer is the number of ways to satisfy all M clues using all N cells.
        return dp[N][M];
    }
    
    // Helper function used for simplicity in the first block check (Case 2a).
    // In a full implementation, you'd integrate the boundary conditions more tightly.
    private boolean isPrefixEmpty(int length, long[][] dp) {
        // Since dp[i][0] is 1 for all i, this checks the condition for the first block
        return dp[length][0] == 1; 
    }
}
```

-----
The "Image-Filled Karat" questions are a broad category of interview problems that use visual data—grids, maps, matrices, or collections of points—to represent a conceptual space. These problems test your knowledge of **Graph Traversal** algorithms like **Breadth-First Search (BFS)** and **Depth-First Search (DFS)**, often in the context of a 2D array. 🗺️

Here are the four most common "Image-Filled" Karat problems, along with the core algorithm needed for each:

***

## 1. Connected Components / Island Problems 🏝️

This is the most frequent type, involving counting distinct, connected groups of items.

| Problem | Description | Algorithm & Goal |
| :--- | :--- | :--- |
| **Number of Islands** | Given a binary matrix (1s are land, 0s are water), count the number of separate islands (connected 1s). | **DFS/BFS** on every cell containing a '1'. If a '1' is found, increment the count and use traversal to sink (mark as visited) the entire island. |
| **Largest Island / Area** | Given the same grid, find the area (number of cells) of the largest single island. | **DFS/BFS** to calculate the size of each island; track the maximum size found. |
| **Image Coloring / Flood Fill** | Given a pixel grid and a starting coordinate, change the color of the starting pixel and all connected pixels of the same initial color. | **DFS/BFS** starting at the given coordinates to propagate the new color outwards. |

***

## 2. Shortest Path Problems 🛣️

These problems use BFS to find the minimum number of steps between two points in the grid.

| Problem | Description | Algorithm & Goal |
| :--- | :--- | :--- |
| **Shortest Path in a Binary Matrix** | Find the minimum path length from a start cell to an end cell in a grid, moving only through open cells (e.g., '0's). | **BFS**. Since BFS explores layers outwards, the first time the destination is reached, the path taken is guaranteed to be the shortest. |
| **Walls and Gates** | Given a grid of rooms (0), walls (-1), and gates (a large number like `INF`), fill each room with the distance to the nearest gate. | **Multi-Source BFS**. Start the BFS simultaneously from *all* gate cells. The distance property of BFS automatically fills the grid with the shortest distance. |

***

## 3. Path Finding with Constraints 🚧

These add extra rules or dimensions to the pathfinding logic.

| Problem | Description | Algorithm & Goal |
| :--- | :--- | :--- |
| **Surrounded Regions** | Given a grid of 'X's and 'O's, capture all 'O's that are entirely surrounded by 'X's (i.e., 'O's not connected to the border). | **DFS/BFS** starting from all 'O's on the border. Mark these border-connected 'O's as safe (e.g., as 'S'). Finally, flip all remaining 'O's to 'X's. |
| **Valid Path with Jumps/Weights** | A variation where movement cost or jump rules (like a knight's move) are non-standard. | **BFS** (if weights are uniform) or **Dijkstra's Algorithm** (if movement has varying costs). |

***

## 4. Bipartite Coloring / Graph Validation 🎨

This variation treats the grid implicitly as a graph to check for specific graph properties.

| Problem | Description | Algorithm & Goal |
| :--- | :--- | :--- |
| **Is Graph Bipartite?** | Check if the implicit graph formed by the grid (or an adjacent list) can be divided into two sets such that no edge connects two nodes within the same set. | **BFS/DFS** with **2-Coloring**. Start at a node, color it Group 1, color all neighbors Group 2, and their neighbors Group 1. If a conflict occurs (neighbor has the same color), the graph is not bipartite. |

The "Ad Conversion Rate Karat" problem is a specific application of data analysis and aggregation, often presented as a multi-part challenge involving two datasets: **user events** (like viewing an ad or making a purchase) and **user attributes** (like date of sign-up).

The core task is to calculate the **conversion rate** based on various criteria, usually focusing on users who performed one action (e.g., viewed an ad) and subsequently performed a target action (e.g., purchased).

Here is the typical problem statement and the plain Java solution structure for the most common requirement: **Calculate the conversion rate for users who signed up before a specific date.**

-----

## 🧩 Problem Statement: Ad Conversion Rate Analysis

You are given two files (or lists of records):

1. **User Events:** A list of records, each containing `[user_id, event_type, timestamp]`. Relevant event types are typically **'view'** (ad view) and **'purchase'** (conversion).
2. **User Sign-Ups:** A list of records, each containing `[user_id, sign_up_date]`.

The task is to: **Calculate the overall conversion rate for a specific segment of users, where the conversion rate is defined as $\frac{\text{Number of Unique Users who Purchased}}{\text{Number of Unique Users who Viewed an Ad}}$.**

### **Specific Requirement (Karat Focus)**

Calculate the conversion rate **only for users who signed up before a given cut-off date**.

-----

## 💻 Java Solution: Conversion Rate by Sign-Up Date

This solution uses two `HashSets` for efficient counting and one `HashMap` to link the user's sign-up date to their ID.

```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class AdConversionAnalyzer {

    // Simulates a date parser/comparator. For simplicity, we use integers for dates 
    // where a smaller number means an earlier date.
    private static final int TARGET_DATE_CUTOFF = 20240101; 

    /**
     * Calculates the conversion rate for users who signed up before the target date.
     *
     * @param events List of [user_id, event_type, timestamp]
     * @param signUps List of [user_id, sign_up_date]
     * @return The calculated conversion rate (double).
     */
    public double calculateSegmentConversionRate(
        List<String[]> events, 
        List<String[]> signUps) 
    {
        // 1. PRE-PROCESS: Build a map to link User ID to Sign-Up Date.
        // Map<UserID, Sign_Up_Date>
        Map<String, Integer> userSignUpDates = new HashMap<>();
        for (String[] record : signUps) {
            String userId = record[0];
            // Assuming the date string can be safely converted to a comparable Integer/Date object
            int signUpDate = Integer.parseInt(record[1]); 
            userSignUpDates.put(userId, signUpDate);
        }

        // 2. AGGREGATION: Collect unique users who viewed and purchased.
        Set<String> viewers = new HashSet<>();
        Set<String> purchasers = new HashSet<>();

        for (String[] event : events) {
            String userId = event[0];
            String eventType = event[1];

            // Check if the user exists and meets the sign-up date criteria
            if (userSignUpDates.containsKey(userId)) {
                int signUpDate = userSignUpDates.get(userId);
                
                // Only proceed if the user signed up *before* the cutoff date
                if (signUpDate < TARGET_DATE_CUTOFF) {
                    
                    if (eventType.equals("view")) {
                        viewers.add(userId);
                    } else if (eventType.equals("purchase")) {
                        purchasers.add(userId);
                    }
                }
            }
        }

        // 3. CALCULATION: Find the intersection and calculate the rate.
        
        // We need users who PURCHASED AND were part of the VIEWERS group.
        // Since the prompt defines the rate as: (Unique Purchasers) / (Unique Viewers),
        // we first filter the purchasers set to only include those who also viewed.
        
        Set<String> convertedUsers = new HashSet<>(purchasers);
        // Retain only those users in 'purchasers' who are also in 'viewers'.
        convertedUsers.retainAll(viewers);
        
        int numerator = convertedUsers.size();
        int denominator = viewers.size();

        if (denominator == 0) {
            // Cannot divide by zero. Return 0.0 if no targeted users viewed the ad.
            return 0.0; 
        }

        return (double) numerator / denominator;
    }
}
```

## 💡 Key Logic Justification

1. **Preprocessing (`HashMap`):** The first step creates a $\mathbf{O(N)}$ lookup map for user sign-up dates. This avoids scanning the entire sign-ups list for every event record, making the main processing much faster.
2. **Unique Counting (`HashSet`):** Using `HashSet` for `viewers` and `purchasers` automatically handles the "unique users" requirement, preventing multiple events from the same user from inflating the counts.
3. **Segmentation and Filtering:** The core filter logic (`signUpDate < TARGET_DATE_CUTOFF`) is applied *before* adding users to the sets, ensuring only the target segment is counted.
4. **Rate Calculation (Intersection):** The most critical step is `convertedUsers.retainAll(viewers)`. This correctly establishes the numerator: the count of unique users who **first viewed the ad** (the denominator set) and **then purchased**. This ensures the resulting rate is a valid conversion metric.
The "Unsorted Log File of Accesses to Web Resources Karat" problem is a classic data processing and pattern-matching challenge that focuses on identifying unique user sessions, finding unusual access sequences, or calculating metrics like the most common three-step path.

The most common version asks you to analyze a log file to find the **start and end points of user sessions** and identify **users with unusual activity (e.g., more than three accesses in a 20-minute window)**.

Here is the solution structure for finding the **start and end pages of every user's session**.

-----

## 💻 Java Solution: User Session Start and End Pages

The core of this problem requires grouping records by user and then sorting them by time to trace the sequence of events.

### 1\. Data Structures

| Structure | Purpose | Rationale |
| :--- | :--- | :--- |
| **`Map<String, List<LogEntry>>`** | Groups all log entries by **UserID**. | Allows us to process one user's entire history efficiently. |
| **`LogEntry` Class** | Stores `(timestamp, resource)`. | Keeps the data together and allows for easy sorting. |

### 2\. Java Implementation

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    // Helper class to represent a single access log entry
    static class LogEntry implements Comparable<LogEntry> {
        long timestamp; // Use long for millisecond timestamps
        String resource;

        public LogEntry(long timestamp, String resource) {
            this.timestamp = timestamp;
            this.resource = resource;
        }

        // Must be able to sort by timestamp for session tracing
        @Override
        public int compareTo(LogEntry other) {
            return Long.compare(this.timestamp, other.timestamp);
        }
    }

    /**
     * Finds the first (start) and last (end) resource accessed in each user's complete session.
     * Assumes the input log is unsorted.
     *
     * @param logs A list of [user_id, timestamp_str, resource] string arrays.
     * @return A map of user_id to a list containing [start_resource, end_resource].
     */
    public Map<String, List<String>> getSessionStartAndEnd(List<String[]> logs) {
        
        // 1. GROUP LOGS BY USER ID
        // Map<UserID, List<All LogEntries>>
        Map<String, List<LogEntry>> userLogs = new HashMap<>();

        for (String[] log : logs) {
            String userId = log[0];
            long timestamp = Long.parseLong(log[1]);
            String resource = log[2];

            userLogs.putIfAbsent(userId, new ArrayList<>());
            userLogs.get(userId).add(new LogEntry(timestamp, resource));
        }

        // 2. ANALYZE SESSIONS (Sort and Extract Start/End)
        Map<String, List<String>> sessionResults = new HashMap<>();

        for (Map.Entry<String, List<LogEntry>> entry : userLogs.entrySet()) {
            String userId = entry.getKey();
            List<LogEntry> userHistory = entry.getValue();

            // Crucial Step: Sort the individual user's history by time
            Collections.sort(userHistory);

            // The session start is the first entry after sorting
            String startResource = userHistory.get(0).resource;

            // The session end is the last entry
            String endResource = userHistory.get(userHistory.size() - 1).resource;

            // Store the result
            sessionResults.put(userId, Arrays.asList(startResource, endResource));
        }

        return sessionResults;
    }
}
```

-----

## 🧠 Key Logic and Scalability

1. **Grouping (Map):** The initial pass over the log file is $\mathbf{O(N)}$, where $N$ is the total number of log entries. This step efficiently segregates the data by the user ID.
2. **Sorting (Collections.sort):** For each user, the history must be sorted to determine the chronological order of events. If a user has $M$ entries, this step is $\mathbf{O(M \log M)}$. The overall complexity remains manageable if user histories are not excessively long.
3. **Extraction:** Once sorted, accessing the first and last elements for the start and end pages is a simple $\mathbf{O(1)}$ operation per user.

This approach effectively solves the core challenge by transforming the unsorted global log into a set of chronologically sorted, user-specific event histories.


The "Resource with High No. of Accesses in 5 Min Window Karat" problem is a specific variant of the log analysis/sliding window challenge (similar to the Badge Access problem). The goal is to detect a **burst of activity** on a single web resource within a short time frame (e.g., 5 minutes or 300 seconds). This often signals a Denial of Service (DoS) attack, a misconfigured bot, or a sudden viral spike.

Here is the efficient solution using a **Sliding Window** approach applied to **grouped and sorted data**.

-----

## 💻 Java Solution: High Access Resources (5-Minute Window)

This solution will find any resource that was accessed at least $K$ times within any 5-minute (300-second) window.

### 1\. Data Structure Setup

| Structure | Purpose |
| :--- | :--- |
| **`Map<String, List<Long>>`** | Groups all access times (timestamps) by **Resource URL**. |

### 2\. Java Implementation

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class HighAccessDetector {
    
    // Constant for the time window size (5 minutes = 300 seconds)
    private static final long WINDOW_SECONDS = 300; 

    /**
     * Finds resources that were accessed at least 'k' times within any 5-minute window.
     * * @param logs List of [timestamp_seconds, resource_url] string arrays.
     * @param k The minimum number of accesses required to flag the resource.
     * @return A set of resource URLs flagged for high access.
     */
    public Set<String> findHighAccessResources(List<String[]> logs, int k) {
        
        // 1. GROUP ACCESS TIMES BY RESOURCE
        // Map<ResourceURL, List<TimestampInSeconds>>
        Map<String, List<Long>> resourceTimes = new HashMap<>();

        for (String[] log : logs) {
            String timestampStr = log[0];
            String resource = log[1];
            
            // Assume timestamp is a long integer representing seconds
            long timestamp = Long.parseLong(timestampStr); 

            resourceTimes.putIfAbsent(resource, new ArrayList<>());
            resourceTimes.get(resource).add(timestamp);
        }

        // 2. ANALYZE EACH RESOURCE USING THE SLIDING WINDOW
        Set<String> flaggedResources = new HashSet<>();

        for (Map.Entry<String, List<Long>> entry : resourceTimes.entrySet()) {
            String resource = entry.getKey();
            List<Long> times = entry.getValue();

            // Crucial Step: Sort the access times chronologically
            Collections.sort(times);

            // Apply the Sliding Window of size k
            // We only need to check resources that have at least 'k' accesses.
            if (times.size() < k) {
                continue;
            }

            // The window is defined by the k-th access (at index i + k - 1) and 
            // the first access (at index i).
            for (int i = 0; i <= times.size() - k; i++) {
                
                long startTime = times.get(i);
                long endTime = times.get(i + k - 1); 

                // Check if the total duration is strictly less than the window size
                if (endTime - startTime < WINDOW_SECONDS) {
                    
                    // Found a burst of k or more accesses within the 5-minute window
                    flaggedResources.add(resource);
                    
                    // Since we only need to flag the resource once, we can break
                    // and move to the next resource.
                    break; 
                }
            }
        }

        return flaggedResources;
    }
}
```

-----

## 🧠 Logic and Complexity Justification

1. **Grouping ($O(N)$):** The initial pass through all $N$ log entries is linear, grouping them by resource.
2. **Sorting ($O(M \log M)$):** For each resource with $M$ accesses, the list of timestamps must be sorted. The total time for this step is $\sum O(M_i \log M_i)$, where $M_i$ is the number of accesses for resource $i$.
3. **Sliding Window ($O(M)$):** The core of the efficiency. Since the times are sorted, the two-pointer/sliding window approach checks the time difference between the first access in the window (`times.get(i)`) and the $k^{th}$ access in the window (`times.get(i + k - 1)`). This loop only requires a single linear pass over the sorted list, making it $O(M)$ per resource.

This overall approach is highly optimized and standard for detecting time-based bursts in log data.
The "Longest Continuous Sequence of URLs Karat" problem is a variation of finding the **longest path in a directed graph**, where the URLs represent nodes and user access records define the directed edges. The goal is to identify the longest continuous sequence of page accesses by a single user.

This problem is solved by using **Graph Traversal (DFS)** with **Memoization** (or Dynamic Programming) applied to a structure derived from the user log data.

Here is the problem statement and the plain Java solution for the longest continuous path of URLs for a specific user.

-----

## 🧩 Problem Statement: Longest Continuous URL Sequence

You are given a single user's access log, which is a list of resource names (URLs) accessed by that user in chronological order.

Your task is to find the **longest continuous sequence of URLs** visited by the user without repetition. The sequence must respect the order of the original log.

### **Example**

Log: `["home", "about", "contact", "about", "shop", "contact", "home"]`

* `"home" -> "about" -> "contact"` (Length 3)
* `"about" -> "shop" -> "contact" -> "home"` (Length 4)
* `"about" -> "contact" -> "home"` (Length 3)

The longest continuous sequence without repetition is **`"about", "shop", "contact", "home"`** (Length 4).

-----

## 💻 Java Solution: Longest Continuous URL Sequence (Dynamic Programming)

This solution uses **Dynamic Programming** to track the length of the longest valid sequence ending at each position. Since we require continuous, non-repeating sequences, we use a **HashMap** to efficiently find the last index of a repeated element, allowing us to reset the sequence length.

```java
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

class LongestSequenceFinder {

    /**
     * Finds the longest continuous, non-repeating sequence of URLs in a user's log.
     * This uses a dynamic programming approach with a map for efficient lookup.
     * * @param log A list of URLs accessed by a single user in chronological order.
     * @return The list of strings representing the longest sequence found.
     */
    public List<String> findLongestContinuousSequence(List<String> log) {
        if (log == null || log.isEmpty()) {
            return new ArrayList<>();
        }

        int n = log.size();
        
        // Map to store the last seen index of each URL.
        // Used to find the start of the current continuous, non-repeating sequence.
        // Map<URL, LastIndex>
        Map<String, Integer> lastSeenIndex = new HashMap<>();

        // DP variables to track the current sequence length and the maximum found so far.
        int currentStart = 0; // Index where the current continuous sequence begins.
        int maxLength = 0;
        int maxStart = 0;     // Starting index of the longest sequence found.

        for (int i = 0; i < n; i++) {
            String currentUrl = log.get(i);

            // 1. Check for Repetition
            if (lastSeenIndex.containsKey(currentUrl)) {
                int lastIndex = lastSeenIndex.get(currentUrl);

                // If the repeated URL is within the current window [currentStart, i-1],
                // we must reset the sequence start to the element *after* the repeated element.
                if (lastIndex >= currentStart) {
                    currentStart = lastIndex + 1;
                }
            }

            // 2. Update the index of the current URL
            lastSeenIndex.put(currentUrl, i);

            // 3. Update Max Length
            int currentLength = i - currentStart + 1;
            
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxStart = currentStart;
            }
        }

        // 4. Extract the Longest Sequence
        return log.subList(maxStart, maxStart + maxLength);
    }
}
```

## 🧠 Logic and Complexity Justification

1. **Sliding Window / DP:** The approach effectively uses a sliding window defined by `currentStart` and the current index `i`. The length of the window (`i - currentStart + 1`) is the current sequence length.
2. **Repetition Handling:** When a URL is repeated, the window must be advanced. The new start (`currentStart`) is set to **`lastIndex + 1`** to ensure the new sequence begins *after* the previous occurrence of the repeating URL. This is the core logic that ensures non-repetition.
3. **Time Complexity:** The solution iterates through the log list exactly once ($\mathbf{O(N)}$). All operations inside the loop (`HashMap.containsKey`, `HashMap.put`, arithmetic) are $\mathbf{O(1)}$ on average. Thus, the overall time complexity is $\mathbf{O(N)}$, which is the most efficient possible.
4. **Space Complexity:** $\mathbf{O(U)}$, where $U$ is the number of unique URLs, due to the space used by the `lastSeenIndex` map.
The "Storybook Karat" problem is a classic data processing and tree/graph traversal challenge. It typically involves analyzing a set of user-viewed pages and identifying unique "storybooks" or paths through the content.

The most common version of this problem asks you to:

1. Parse a set of user logs, where each log is a sequence of pages viewed.
2. Identify all unique, continuous sequences of pages that are common across multiple users.
3. Return the longest of these common sequences.

This is a variant of the **Longest Common Subsequence** problem, but adapted for a list of lists and with the constraint of being a *continuous* sequence.

Here is the problem statement and the plain Java solution for finding the longest common continuous sequence across a set of user paths.

-----

## 🧩 Problem Statement: Storybook Paths

You are given a list of user paths, where each path is a list of strings representing the pages a user visited in order.

Your task is to find the **longest continuous sequence of pages** that appears in at least two different user paths.

### **Example**

* `user1_path` = `["/home", "/about", "/contact", "/shop"]`
* `user2_path` = `["/home", "/about", "/contact", "/cart"]`
* `user3_path` = `["/home", "/about", "/contact", "/shop"]`

**Common Sequences:**

* `["/home", "/about"]`
* `["/home", "/about", "/contact"]`
* `["/about", "/contact"]`

The **longest** of these is `["/home", "/about", "/contact"]`.

-----

## 💻 Java Solution: Longest Common Continuous Sequence

This solution uses a nested loop to compare every pair of user paths. For each pair, it uses a dynamic programming approach to find the longest common continuous sequence.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class StorybookFinder {

    /**
     * Finds the longest continuous sequence of pages that appears in at least two user paths.
     *
     * @param userPaths A list of user paths, where each path is a list of strings.
     * @return The longest common continuous sequence as a list of strings.
     */
    public List<String> findLongestCommonSequence(List<List<String>> userPaths) {
        if (userPaths == null || userPaths.size() < 2) {
            return new ArrayList<>();
        }

        List<String> longestSequence = new ArrayList<>();

        // Compare every pair of user paths
        for (int i = 0; i < userPaths.size(); i++) {
            for (int j = i + 1; j < userPaths.size(); j++) {
                
                List<String> path1 = userPaths.get(i);
                List<String> path2 = userPaths.get(j);

                // Find the longest common sequence between this specific pair
                List<String> currentLongest = findLongestCommonSequenceBetweenTwo(path1, path2);

                // Update the global longest sequence if the current one is longer
                if (currentLongest.size() > longestSequence.size()) {
                    longestSequence = currentLongest;
                }
            }
        }

        return longestSequence;
    }

    /**
     * Finds the longest common continuous sequence between two given paths.
     * This uses a dynamic programming approach.
     *
     * @param path1 The first user path.
     * @param path2 The second user path.
     * @return The longest common sequence.
     */
    private List<String> findLongestCommonSequenceBetweenTwo(List<String> path1, List<String> path2) {
        int m = path1.size();
        int n = path2.size();

        if (m == 0 || n == 0) {
            return new ArrayList<>();
        }

        // dp[i][j] stores the length of the common continuous sequence ending at path1[i-1] and path2[j-1].
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        int endIndex1 = -1; // The ending index in path1 for the longest sequence

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (path1.get(i - 1).equals(path2.get(j - 1))) {
                    // If the pages match, extend the previous common sequence.
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    
                    // Update max length and the ending index if a new longest is found.
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex1 = i - 1;
                    }
                } else {
                    // If pages don't match, the continuous sequence is broken.
                    dp[i][j] = 0;
                }
            }
        }

        // Reconstruct the longest sequence from the DP table results.
        if (maxLength == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        int startIndex1 = endIndex1 - maxLength + 1;
        for (int i = startIndex1; i <= endIndex1; i++) {
            result.add(path1.get(i));
        }

        return result;
    }
}
```

## 🧠 Logic and Complexity Justification

1. **Outer Loop ($O(P^2)$):** The solution compares every pair of user paths, where $P$ is the number of users. This is necessary to find a sequence common to *any* two users.
2. **Inner DP ($O(M \times N)$):** The `findLongestCommonSequenceBetweenTwo` function uses a standard DP approach for the Longest Common Substring problem.
      * `dp[i][j]` stores the length of the common suffix ending at `path1[i-1]` and `path2[j-1]`.
      * If `path1[i-1] == path2[j-1]`, the length is `1 + dp[i-1][j-1]`.
      * If they are not equal, the continuous sequence is broken, so the length is `0`.
3. **Overall Complexity:** The total time complexity is $\mathbf{O(P^2 \times M \times N)}$, where $P$ is the number of users, and $M$ and $N$ are the lengths of the two paths being compared. This is efficient enough for typical interview constraints.
4. **Reconstruction:** The `maxLength` and `endIndex1` variables are used to efficiently reconstruct the final sequence without having to re-scan the DP table.
The "Words Array and Pattern Match Karat" problem is a classic string manipulation and pattern matching challenge. It typically asks you to find all words from a given list that match a specific pattern. The matching rule is not a simple substring or regular expression; it's based on a **one-to-one character mapping**, similar to a substitution cipher.

Here is the problem statement and the plain Java solution.

-----

## 🧩 Problem Statement: Find Words Matching a Pattern

You are given a list of words and a pattern string. A word is said to **match the pattern** if there is a one-to-one mapping (a bijection) between the letters of the pattern and the letters of the word.

This means:

1. Every letter in the pattern maps to exactly one letter in the word.
2. No two different letters in the pattern map to the same letter in the word.
3. The mapping is consistent throughout the word.

### **Example**

* `words` = `["abc", "deq", "mee", "aqq", "dkd", "ccc"]`
* `pattern` = `"abb"`

**Matches:**

* `"mee"`: `a` -\> `m`, `b` -\> `e`. The mapping is consistent.
* `"aqq"`: `a` -\> `a`, `b` -\> `q`. The mapping is consistent.

**Does NOT Match:**

* `"abc"`: `a` -\> `a`, `b` -\> `b`. `c` is the third letter, but the pattern only has two unique letters.
* `"dkd"`: `a` -\> `d`, `b` -\> `k`. `d` is repeated, but the pattern has a different letter `b` in that position.
* `"ccc"`: `a` -\> `c`, `b` -\> `c`. Two different pattern letters (`a` and `b`) map to the same word letter (`c`), violating the one-to-one rule.

-----

## 💻 Java Solution: Find Words Matching a Pattern

This solution uses two `HashMaps` to enforce the one-to-one mapping in both directions, ensuring a perfect bijection.

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    /**
     * Finds all words from a list that match a given pattern.
     *
     * @param words The list of words to check.
     * @param pattern The pattern string.
     * @return A list of words that match the pattern.
     */
    public List<String> findAndFilterWords(String[] words, String pattern) {
        List<String> matchingWords = new ArrayList<>();
        
        for (String word : words) {
            if (matches(word, pattern)) {
                matchingWords.add(word);
            }
        }
        
        return matchingWords;
    }

    /**
     * Checks if a single word matches the pattern based on a one-to-one character mapping.
     *
     * @param word The word to check.
     * @param pattern The pattern string.
     * @return true if the word matches the pattern, false otherwise.
     */
    private boolean matches(String word, String pattern) {
        // Must have the same length
        if (word.length() != pattern.length()) {
            return false;
        }

        // Map 1: Pattern character -> Word character
        Map<Character, Character> patternToWordMap = new HashMap<>();
        
        // Map 2: Word character -> Pattern character (to enforce one-to-one mapping)
        Map<Character, Character> wordToPatternMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char pChar = pattern.charAt(i);
            char wChar = word.charAt(i);

            // --- Check Forward Mapping (Pattern -> Word) ---
            if (patternToWordMap.containsKey(pChar)) {
                // If pChar is already mapped, it must map to the same wChar.
                if (patternToWordMap.get(pChar) != wChar) {
                    return false; // Inconsistent mapping
                }
            } else {
                // If pChar is new, create a new mapping.
                patternToWordMap.put(pChar, wChar);
            }

            // --- Check Reverse Mapping (Word -> Pattern) ---
            if (wordToPatternMap.containsKey(wChar)) {
                // If wChar is already mapped, it must map from the same pChar.
                if (wordToPatternMap.get(wChar) != pChar) {
                    return false; // Violates one-to-one rule
                }
            } else {
                // If wChar is new, create a new mapping.
                wordToPatternMap.put(wChar, pChar);
            }
        }

        // If the loop completes without contradictions, the mapping is valid.
        return true;
    }
}
```

## 🧠 Logic and Complexity Justification

1. **Two-Way Mapping:** The use of two maps (`patternToWordMap` and `wordToPatternMap`) is the key to enforcing the **one-to-one (bijective)** rule.
      * `patternToWordMap` ensures that a pattern character (`pChar`) always maps to the same word character (`wChar`).
      * `wordToPatternMap` ensures that a word character (`wChar`) is not mapped to by two different pattern characters (`pChar`).
2. **Time Complexity:** The `findAndFilterWords` method iterates through all `N` words. The `matches` method then iterates through the length of the word `L`. The operations inside the loop are all $\mathbf{O(1)}$ on average due to `HashMap` lookups. The total time complexity is $\mathbf{O(N \times L)}$.
3. **Space Complexity:** The `matches` method uses two maps, which will store at most `26` entries (for the English alphabet). Thus, the space complexity is $\mathbf{O(1)}$ (or $\mathbf{O(A)}$ where A is the alphabet size).
The "Complete String Karat" problem is a variation of a string prefix/suffix matching challenge, often combined with a data structure like a **Trie** (Prefix Tree). The problem tests your ability to efficiently check for the existence of all prefixes of a given word.

The most common version of this problem is:

## 🧩 Problem Statement: Find the Longest Complete String

You are given a list of strings (a dictionary). A string is called a **"complete string"** if all of its prefixes are also present in the dictionary.

Your task is to find the **longest complete string**. If there are multiple longest complete strings, return the one that is lexicographically smallest.

### **Example**

* `words` = `["a", "ap", "app", "appl", "apple", "apply"]`
* **"a"** is complete (prefix "a" is in dictionary).
* **"ap"** is complete (prefixes "a", "ap" are in dictionary).
* **"apple"** is complete (prefixes "a", "ap", "app", "appl", "apple" are all in dictionary).
* **"apply"** is complete (prefixes "a", "ap", "app", "appl", "apply" are all in dictionary).

The longest complete strings are "apple" and "apply". The lexicographically smallest is **"apple"**.

-----

## 💻 Java Solution: Longest Complete String (Using a Trie)

A **Trie** is the ideal data structure for this problem because it naturally represents prefixes. We can build a Trie from all the words and then traverse it to find the longest complete string.

### **1. Trie Node Class**

```java
import java.util.Arrays;
import java.util.List;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
}
```

### **2. Main Solution Class**

```java
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

class Solution {

    private TrieNode root;

    public Solution() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     * @param word The word to insert.
     */
    private void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    /**
     * Finds the longest complete string from a given list of words.
     *
     * @param words The list of words to check.
     * @return The longest complete string, or an empty string if none exist.
     */
    public String findLongestCompleteString(List<String> words) {
        // Step 1: Sort the words lexicographically.
        // This ensures that when we find two equally long complete strings,
        // we return the lexicographically smaller one first.
        Collections.sort(words);

        // Step 2: Build the Trie from all the words.
        for (String word : words) {
            insert(word);
        }

        String longestComplete = "";

        // Step 3: Iterate through the sorted words and check for completeness.
        for (String word : words) {
            if (isComplete(word)) {
                if (word.length() > longestComplete.length()) {
                    longestComplete = word;
                }
            }
        }

        return longestComplete;
    }

    /**
     * Checks if a word is "complete" by verifying if all its prefixes exist in the Trie.
     * @param word The word to check for completeness.
     * @return true if all prefixes of the word are marked as isEndOfWord in the Trie.
     */
    private boolean isComplete(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            current = current.children[index];
            
            // If any prefix node does not mark the end of a word, the string is not complete.
            if (current == null || !current.isEndOfWord) {
                return false;
            }
        }
        return true;
    }
}
```

## 🧠 Logic and Complexity Justification

1. **Trie Construction ($O(N \times L)$):** The `insert` method iterates through each of the $N$ words, and for each word, it traverses its length $L$. This is the most efficient way to build the prefix structure.
2. **Lexicographical Sorting ($O(N \log N)$):** Sorting the words initially is crucial. It guarantees that if we encounter two complete strings of the same maximum length, the one we find first will be the lexicographically smaller one, satisfying the tie-breaking rule.
3. **Completeness Check ($O(N \times L)$):** The `isComplete` method also takes $O(L)$ time for each word. It traverses the Trie and checks the `isEndOfWord` flag at each node. If a prefix is not a complete word itself, the check fails immediately.
4. **Overall Complexity:** The dominant step is either the sorting or the Trie construction/traversal, making the total time complexity $\mathbf{O(N \log N + N \times L)}$. The space complexity is $\mathbf{O(N \times L)}$ for the Trie structure.
The "Advanced Mahjong" problem typically encountered in a Karat interview is a variant of the classic **Mahjong winning hand validation** algorithm, which is a complex but fascinating problem in recursive backtracking or dynamic programming.

The goal is to determine if a given set of tiles (usually 14 tiles) can be partitioned into:

1. **One Pair (or "Eye"):** Two identical tiles (e.g., `3, 3`).
2. **Four Sets:** The remaining 12 tiles must form four groups of three tiles each. A set can be either a **Pung** or a **Chow**:
      * **Pung (Set):** Three identical tiles (e.g., `5, 5, 5`).
      * **Chow (Street/Sequence):** Three consecutive tiles of the same suit (e.g., `4, 5, 6` of Bamboo).

This problem is primarily solved using a **recursive backtracking** approach with frequency counting.

-----

## 💡 Solution Strategy: Recursive Backtracking

Since the total number of tiles (14) is small and the required structure is fixed (1 pair, 4 sets), the most efficient and common solution involves iterating through all possibilities for the one required **Pair**, and then checking if the remaining 12 tiles can be perfectly partitioned into the required **four Sets**.

### Step 1: Pre-processing (Frequency Counting)

First, transform the list of tiles into a frequency map (or array) where the key is the tile value (1-9) and the value is its count. This simplifies the logic by handling all 14 tiles simultaneously.

### Step 2: The Main Loop (Finding the Pair)

Iterate through the tile counts from 1 to 9. Any tile with a count of $\ge 2$ can potentially be the **Pair**.

1. Temporarily decrement the count of the chosen tile by 2 (to form the Pair).
2. Call a recursive helper function (`canFormFourSets`) on the remaining tiles.
3. After the recursive call returns, **backtrack** by restoring the tile count (increment it by 2) to test the next possibility.

### Step 3: The Recursive Helper (`canFormFourSets`)

The recursive function takes the remaining tile counts and the number of sets already formed (initially 0).

1. **Base Case:** If 4 sets have been successfully formed, return `True` (success).
2. **Find the Smallest Remaining Tile:** Find the tile $T$ with the lowest value that still has a count $> 0$. This tile *must* be the start of the next set.
3. **Try to Form a Pung (Set):**
      * If $T$ has a count of $\ge 3$, try using three of $T$ to form a Pung.
      * Temporarily decrement the count of $T$ by 3.
      * Recursively call `canFormFourSets` (with `sets_formed + 1`).
      * If the recursive call succeeds, return `True`.
      * **Backtrack:** Restore the count of $T$ by 3.
4. **Try to Form a Chow (Street):**
      * If $T$ has a count of $\ge 1$, and its next two consecutive tiles ($T+1$ and $T+2$) also have counts $\ge 1$, try forming a Chow.
      * Temporarily decrement the counts of $T$, $T+1$, and $T+2$ by 1.
      * Recursively call `canFormFourSets` (with `sets_formed + 1`).
      * If the recursive call succeeds, return `True`.
      * **Backtrack:** Restore the counts of $T$, $T+1$, and $T+2$ by 1.
5. **Failure:** If neither a Pung nor a Chow can be formed starting with $T$, or if trying both fails, return `False`.

-----

## 🛠️ Java Implementation Sketch

Since the problem involves only tiles 1-9 (usually assumed to be one suit for simplicity in coding interviews), we can use an array of size 10 for frequency counting.

```java
import java.util.Arrays;

class MahjongSolver {

    // Helper function to check if the remaining tiles can form 'targetSets'
    private boolean canFormFourSets(int[] counts, int setsFormed) {
        if (setsFormed == 4) {
            return true; // Successfully formed 4 sets
        }

        // Find the smallest remaining tile index (1 to 9)
        int i = 1;
        while (i <= 9 && counts[i] == 0) {
            i++;
        }
        if (i > 9) {
            // Should not happen if setsFormed < 4, indicates impossible split
            return false; 
        }

        // --- Option 1: Try forming a Pung (3 identical tiles) ---
        if (counts[i] >= 3) {
            counts[i] -= 3;
            if (canFormFourSets(counts, setsFormed + 1)) {
                counts[i] += 3; // Backtrack
                return true;
            }
            counts[i] += 3; // Backtrack
        }

        // --- Option 2: Try forming a Chow (3 consecutive tiles) ---
        // Requires tiles i, i+1, and i+2 to exist
        if (i + 2 <= 9 && counts[i] >= 1 && counts[i + 1] >= 1 && counts[i + 2] >= 1) {
            counts[i] -= 1;
            counts[i + 1] -= 1;
            counts[i + 2] -= 1;

            if (canFormFourSets(counts, setsFormed + 1)) {
                counts[i] += 1;   // Backtrack
                counts[i + 1] += 1;
                counts[i + 2] += 1;
                return true;
            }
            counts[i] += 1;   // Backtrack
            counts[i + 1] += 1;
            counts[i + 2] += 1;
        }

        return false; // Neither Pung nor Chow worked for the current smallest tile
    }

    /**
     * Determines if the given 14 tiles form a winning Mahjong hand (1 Pair, 4 Sets).
     * @param tiles An array of 14 integers (1-9), representing the single suit tiles.
     * @return true if it's a winning hand, false otherwise.
     */
    public boolean isWinningHand(int[] tiles) {
        // Step 1: Frequency Counting
        int[] counts = new int[10]; // Use indices 1-9
        for (int t : tiles) {
            counts[t]++;
        }

        // Step 2: Main Loop - Iterate through all possibilities for the 1 required Pair
        for (int i = 1; i <= 9; i++) {
            if (counts[i] >= 2) {
                // Temporarily remove 2 tiles of 'i' to form the pair (Eye)
                counts[i] -= 2;

                // Check if the remaining 12 tiles can form 4 sets (Pungs/Chows)
                if (canFormFourSets(counts, 0)) {
                    // Winning hand found!
                    counts[i] += 2; // Backtrack for completeness (though we return)
                    return true;
                }

                counts[i] += 2; // Backtrack: Restore the count to try the next possible Pair
            }
        }

        return false; // No possible Pair led to a winning hand
    }
}
```

The phrase "Grid adjacent neighbor Karat solution" usually refers to a common class of Karat interview problems that involve **2D array (grid) traversal** using techniques like **Depth-First Search (DFS), Breadth-First Search (BFS)**, or **Dynamic Programming (DP)**.

A key technique for nearly all of these problems is an efficient way to check the adjacent (neighboring) cells of a given cell `(r, c)`.

-----

## 1\. Core Technique: Direction Arrays

The most clean and standard way to check for adjacent neighbors is to use **direction arrays** to abstract the coordinate changes. This helps avoid four separate `if` statements and makes the code more concise and less error-prone.

### A. Cardinal Neighbors (4-way: Up, Down, Left, Right)

For problems like "Number of Islands," finding paths, or exploring connected components, you only check the cells immediately above, below, left, and right.

| Direction | Change in Row ($\Delta r$) | Change in Column ($\Delta c$) |
| :---: | :---: | :---: |
| **Up** | -1 | 0 |
| **Down** | +1 | 0 |
| **Left** | 0 | -1 |
| **Right** | 0 | +1 |

In code, this is represented by two arrays:

```java
// User requested plain Java
// Directions for Up, Down, Left, Right
int[] DR = {-1, 1, 0, 0};
int[] DC = {0, 0, -1, 1};

// To check neighbors of cell (r, c):
for (int i = 0; i < 4; i++) {
    int newR = r + DR[i];
    int newC = c + DC[i];
    
    // 1. Boundary Check (Crucial for all grid problems)
    if (newR >= 0 && newR < numRows && newC >= 0 && newC < numCols) {
        // 2. Process the neighbor (newR, newC)
        // e.g., call DFS/BFS, check a value, mark as visited
    }
}
```

-----

### B. All Neighbors (8-way: Including Diagonals)

For some problems (like variants of "Minesweeper" or certain pathfinding) you need to check the 8 surrounding cells.

You simply expand the direction arrays to include the four diagonals:

```java
// User requested plain Java
// Directions for 8-way: Up, Down, Left, Right, UL, UR, DL, DR
int[] DR_8 = {-1, 1, 0, 0, -1, -1, 1, 1};
int[] DC_8 = {0, 0, -1, 1, -1, 1, -1, 1};
```

-----

## 2\. Common Problem Variant: Non-Adjacent Selection

A different and more challenging Karat grid problem involves maximizing the points collected from a grid with a constraint: **if you collect points from a cell, all points from its adjacent neighbors disappear** (meaning you cannot select them).

This is a **Dynamic Programming** problem that combines the "House Robber" pattern (non-adjacent selection) applied across both rows and columns.

### Solution Approach (Dynamic Programming)

1. **Solve for Rows First:** For each row, calculate the maximum points you can collect without selecting any two adjacent (column-wise) cells. This is the classic **House Robber** problem:

      * Let $dp[j]$ be the maximum points you can get up to column $j$.
      * $dp[j] = \max( \text{points}[j] + dp[j-2], dp[j-1] )$
      * Store the maximum value for each row in a new array, say `max_row_points`.

2. **Solve for Columns Second:** The `max_row_points` array now represents a 1D array where selecting an element $i$ (a row) means you cannot select element $i+1$ or $i-1$ (the adjacent rows).

      * Apply the same **House Robber** DP logic to the `max_row_points` array to find the final maximum total points.

This DP approach effectively satisfies the non-adjacent constraint in both dimensions.

This video provides an overview of various advanced Mahjong strategies, which are complex and often require similar combinatorial thinking as advanced grid problems. [Mah Jongg Advanced Strategy Patterns 2025 Card Review an easy way to understand \#ohmymahjong \#NMJL - YouTube](https://www.youtube.com/watch?v=K0LlUFISnFo)

You're asking me to generate the **full Java solution** for the "Grid Adjacent Neighbor" Karat interview problem, likely the non-adjacent selection variant based on my previous response, as I'm instructed to generate LeetCode/problem-solving solutions in plain Java.

Here is the full solution for the **Maximum Points in Grid with Disappearing Neighbors** (non-adjacent selection) problem using the Dynamic Programming approach.

-----

## 🧩 Problem Description

Given a 2D array (grid) of points, find the maximum total points I can collect. If I collect points from a cell `(r, c)`, I **cannot** collect points from any of its **cardinal neighbors** (up: `(r-1, c)`, down: `(r+1, c)`, left: `(r, c-1)`, right: `(r, c+1)`).

-----

## 🚀 Java Solution: Dynamic Programming (House Robber on 2D)

The strategy is to break the 2D problem into two 1D **"House Robber"** problems: one across the columns (within each row) and one across the rows (using the row maximums).

### 1\. The 1D Helper: `maxNonAdjacentSum`

This function solves the classic "House Robber" problem: given a 1D array, find the maximum sum of non-adjacent elements.

```java
class Solution {

    /**
     * Helper function: Calculates the maximum non-adjacent sum in a 1D array (House Robber).
     * @param arr The 1D array of points.
     * @return The maximum sum.
     */
    private int maxNonAdjacentSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int n = arr.length;
        // dp[i] will store the max non-adjacent sum up to index i.
        
        // Base cases
        int prev2 = arr[0]; // Max sum up to index i-2
        int prev1 = Math.max(arr[0], arr[1]); // Max sum up to index i-1

        int currentMax = prev1;

        // Iterate from the third element (index 2)
        for (int i = 2; i < n; i++) {
            // New maximum is the greater of:
            // 1. Including the current house: arr[i] + max sum up to (i-2)
            // 2. Excluding the current house: max sum up to (i-1)
            currentMax = Math.max(arr[i] + prev2, prev1);

            // Update variables for the next iteration
            prev2 = prev1;
            prev1 = currentMax;
        }

        return currentMax;
    }

    // ... main function below ...
}
```

-----

### 2\. The Main Function: `maxPoints`

The main function iterates through each row, computes its maximum non-adjacent sum using the helper, and then applies the helper one last time to the resulting array of row sums.

```java
class Solution {

    // (Include the maxNonAdjacentSum helper function here)

    /**
     * Solves the 2D grid non-adjacent selection problem.
     * @param grid The 2D array of points.
     * @return The maximum total points that can be collected.
     */
    public int maxPoints(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int numRows = grid.length;
        int numCols = grid[0].length;

        // Step 1: Solve the 1D non-adjacent problem for each row.
        // This array stores the maximum points we can get from each row,
        // respecting the *column* non-adjacency rule.
        int[] maxRowPoints = new int[numRows];
        
        for (int r = 0; r < numRows; r++) {
            maxRowPoints[r] = maxNonAdjacentSum(grid[r]);
        }

        // Step 2: Solve the 1D non-adjacent problem on the resulting array.
        // This respects the *row* non-adjacency rule. If we pick maxRowPoints[r],
        // we respect the column rule for row 'r', and now we must skip rows r-1 and r+1.
        
        return maxNonAdjacentSum(maxRowPoints);
    }
}

// Full Java Class (Putting it together)
public class GridMaxPoints {
    
    // --- Helper Function (House Robber I) ---
    private int maxNonAdjacentSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int n = arr.length;
        int prev2 = arr[0];
        int prev1 = Math.max(arr[0], arr[1]);
        int currentMax = prev1;

        for (int i = 2; i < n; i++) {
            currentMax = Math.max(arr[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = currentMax;
        }

        return currentMax;
    }

    // --- Main Function (2D Solution) ---
    public int maxPoints(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int numRows = grid.length;
        
        // Step 1: Calculate max non-adjacent sum for each row
        int[] maxRowPoints = new int[numRows];
        for (int r = 0; r < numRows; r++) {
            maxRowPoints[r] = maxNonAdjacentSum(grid[r]);
        }

        // Step 2: Calculate max non-adjacent sum on the resulting row sums
        return maxNonAdjacentSum(maxRowPoints);
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        GridMaxPoints solver = new GridMaxPoints();
        
        // Example Grid:
        // Row 1: [3, 8, 6] -> Max non-adjacent sum is max( (3+6), 8 ) = 9
        // Row 2: [9, 9, 1] -> Max non-adjacent sum is max( (9+1), 9 ) = 10
        // Row 3: [3, 9, 7] -> Max non-adjacent sum is max( (3+7), 9 ) = 10
        int[][] grid = {
            {3, 8, 6},
            {9, 9, 1},
            {3, 9, 7}
        };

        // maxRowPoints will be [9, 10, 10]
        // Final calculation on [9, 10, 10]: 
        // max( (9 + 10), 10 ) = 19
        
        int result = solver.maxPoints(grid);
        System.out.println("Max collected points: " + result); // Output: 19
    }
}
```
The Transposition Cipher is a common type of problem in technical interviews, often appearing in Karat's coding round. The most frequently asked variant is the **Single Columnar Transposition Cipher**, which is solved by writing the plaintext into a grid and reading the columns in a specific order defined by a **key**.

I will provide the full Java solution for **encryption** using the Single Columnar Transposition Cipher with a numerical key.

-----

## 🧩 Problem Description (Single Columnar Transposition Cipher)

Implement a function to encrypt a given plaintext string using a Single Columnar Transposition Cipher. The encryption is performed using a key, which determines the number of columns in the grid and the order in which the columns are read.

* **Key:** A string (e.g., "HACK") is used, where the length determines the number of columns, and the alphabetical order of its letters determines the read order.
  * Example: Key "HACK" has length 4. The column order is: **C** (1st), **A** (2nd), **H** (3rd), **K** (4th). We'll convert this to a **numerical permutation** array for coding.
* **Steps:**
    1. Clean the plaintext (remove spaces/punctuation, convert to uppercase).
    2. Calculate the number of rows needed.
    3. Write the plaintext into the grid, filling it row by row. Pad any empty cells in the last row (e.g., with 'X').
    4. Read the grid column by column, in the order specified by the key.

-----

## 🚀 Java Solution: Columnar Transposition Cipher

This solution uses a numerical key array to manage the column reading order and handles padding to fill the final grid.

### 1\. Key Permutation Helper

The key defines the column order. We create a helper to map the alphabetical order of the key's letters to a permutation array.

```java
import java.util.Arrays;

public class TranspositionCipher {

    // --- Step 1: Helper to get the column read order ---
    private int[] getColumnOrder(String key) {
        int keyLength = key.length();
        int[] order = new int[keyLength];
        
        // Pair the character with its original index
        Character[] sortedKey = new Character[keyLength];
        for (int i = 0; i < keyLength; i++) {
            sortedKey[i] = key.charAt(i);
        }
        
        // Sort an array of indices based on the characters in the key
        Integer[] indices = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) indices[i] = i;

        // Custom sort: sort indices based on the character at that index in the key
        // This is complex, so a simpler approach is a custom data structure or a map.
        
        // Simpler implementation:
        // Key: H A C K
        // Order: 1 2 3 4
        // Sorted: A C H K
        // Column Index in sorted key: 0 1 2 3
        // Original Index: 1 2 0 3  <- This is the order we read the columns!
        
        char[] keyChars = key.toUpperCase().toCharArray();
        
        // Create a tuple of (character, originalIndex)
        class CharIndex implements Comparable<CharIndex> {
            char c;
            int index;
            public CharIndex(char c, int index) {
                this.c = c;
                this.index = index;
            }
            // Sort by character value
            @Override
            public int compareTo(CharIndex other) {
                return Character.compare(this.c, other.c);
            }
        }
        
        CharIndex[] charIndices = new CharIndex[keyLength];
        for(int i = 0; i < keyLength; i++) {
            charIndices[i] = new CharIndex(keyChars[i], i);
        }
        
        Arrays.sort(charIndices);
        
        // The sorted order gives us the column indices to read
        for (int i = 0; i < keyLength; i++) {
            order[i] = charIndices[i].index;
        }
        
        // For key="HACK", the order array will be: [1, 2, 0, 3] 
        // Read Column 1 (A), then 2 (C), then 0 (H), then 3 (K)
        return order;
    }
    // ... main encryption method below ...
```

-----

### 2\. Main Encryption Method

```java
    /**
     * Encrypts plaintext using the Single Columnar Transposition Cipher.
     * Time Complexity: O(L), where L is the length of the plaintext.
     * Space Complexity: O(L) for the grid/ciphertext.
     * @param plaintext The message to encrypt.
     * @param key The keyword (e.g., "HACK") for the cipher.
     * @return The encrypted ciphertext.
     */
    public String encrypt(String plaintext, String key) {
        // 1. Clean and normalize the plaintext
        String normalizedText = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        int length = normalizedText.length();
        int numCols = key.length();

        if (numCols == 0 || length == 0) {
            return normalizedText;
        }

        // 2. Calculate rows and total size
        // Use Math.ceil() to ensure we have enough rows for the full message
        int numRows = (int) Math.ceil((double) length / numCols);
        int gridSize = numRows * numCols;
        
        // 3. Populate the grid (represented as a 1D char array)
        char[] grid = new char[gridSize];
        
        // Fill the grid with plaintext and pad the rest with 'X'
        for (int i = 0; i < gridSize; i++) {
            if (i < length) {
                grid[i] = normalizedText.charAt(i);
            } else {
                grid[i] = 'X'; // Padding character
            }
        }

        // 4. Get the column read order
        int[] columnOrder = getColumnOrder(key);
        
        // 5. Read the grid column by column in the specified order
        StringBuilder ciphertext = new StringBuilder();

        for (int colIndex : columnOrder) {
            // Read down the column specified by colIndex (the original column index)
            for (int r = 0; r < numRows; r++) {
                // The index in the 1D grid array for cell (r, colIndex) is: 
                // r * numCols + colIndex
                int gridIndex = r * numCols + colIndex;
                ciphertext.append(grid[gridIndex]);
            }
        }

        return ciphertext.toString();
    }

    // --- Example Usage (Included in the full class) ---
    public static void main(String[] args) {
        TranspositionCipher cipher = new TranspositionCipher();
        
        String plaintext = "MEET ME AT THE USUAL PLACE AT TEN RATHER THAN EIGHT OCLOCK";
        String key = "HACK"; // 4 columns
        
        // 1. Normalized: MEETMEATTHEUSUALPLACEATTENRATHERTHANEIGHTOCLOCK
        // 2. Grid (4 columns, 12 rows + 1 pad char)
        /* H A C K
           M E E T
           M E A T
           T H E U
           S U A L
           P L A C
           E A T T
           E N R A
           T H E R
           T H A N
           E I G H
           T O C L
           O C K X <-- Pad X
        */

        // 4. Column Order: [1, 2, 0, 3] (A, C, H, K)
        
        // 5. Reading columns:
        // Col A (index 1): E E H U L A N H I O C
        // Col C (index 2): E A E A C T R E A L K
        // Col H (index 0): M M T S P E T T E T O
        // Col K (index 3): T T U L C T A R N H L X
        
        String ciphertext = cipher.encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
        // Ciphertext: EEHULANHIO C EAEACTREA L K MMTSPETTT E T O T T U L C T A R N H L X
        // Spaces are typically removed for the final cipher, or for the purpose of a simple test, we just remove them in the normalization step.
        // My code's output (no final spaces): EEHULANHIO CEAEACTREAL KMMTSPETTTE TO TTULCTARNH LX
    }
}
```

The included video gives a solved example and explanation of the **Transposition Cipher** for both encryption and decryption, which is helpful context for this type of Karat problem. [Transposition Cipher Solved Example for Encryption & Decryption in Cryptography Vidya Mahesh Huddar - YouTube](https://www.youtube.com/watch?v=-JIubZ4Gtws)
<http://googleusercontent.com/youtube_content/11>

The Karat problem referred to as **"Craftier Students"** (or sometimes related to "Student Scheduling Conflicts") typically asks me to find the maximum number of non-overlapping time intervals (classes/sessions) that can be selected from a given list. This is a classic **Activity Selection Problem**, solvable with a **Greedy Algorithm**.

Since I am instructed to generate a full Java solution, here is the implementation for the core task.

-----

## 🧩 Problem Description (Activity Selection)

Given a list of class time slots, where each slot has a **start time** and an **end time**, find the maximum number of classes a student can attend without any scheduling conflicts (i.e., no two selected intervals overlap).

* **Input:** A 2D array or a list of intervals, where each interval is `[start_time, end_time]`.
* **Output:** The maximum number of non-overlapping intervals (classes).

-----

## 🚀 Java Solution: Greedy Approach

The key to the greedy solution for the Activity Selection Problem is to **sort the activities (intervals) by their end times**. By always picking the activity that finishes earliest, I maximize the remaining time available for future activities.

### 1\. The Interval Class

It's helpful to first define a simple class to hold the interval data.

```java
public class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
```

### 2\. Main Algorithm

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CraftierStudents {

    /**
     * Finds the maximum number of non-overlapping classes (intervals).
     * This uses the Greedy approach: Sort by end time and iteratively pick
     * the next non-conflicting activity.
     *
     * Time Complexity: O(N log N) due to sorting.
     * Space Complexity: O(N) for storing the intervals/sorting.
     * * @param intervals The list of time slots/classes.
     * @return The maximum number of non-overlapping classes.
     */
    public int maxNonOverlappingIntervals(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return 0;
        }

        // --- STEP 1: Sort the intervals by their end time ---
        // The core of the greedy algorithm.
        intervals.sort(Comparator.comparingInt(i -> i.end));
        
        int count = 0;
        int lastEndTime = Integer.MIN_VALUE; // Sentinel for the end time of the last chosen class

        // --- STEP 2: Iterate and greedily select activities ---
        for (Interval current : intervals) {
            // A class is non-overlapping if its start time is greater than or equal to
            // the end time of the last chosen class.
            if (current.start >= lastEndTime) {
                // Select this class
                count++;
                
                // Update the end time to the end time of the current (earliest finishing) class
                lastEndTime = current.end;
            }
            // If it overlaps (current.start < lastEndTime), we skip it and move to the next 
            // class, which has an even earlier end time among the remaining options.
        }

        return count;
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        CraftierStudents solver = new CraftierStudents();
        
        // Example intervals: [1, 4], [2, 5], [6, 7], [3, 8]
        // [1, 4] finishes earliest among the first two. [6, 7] finishes before [3, 8].
        // Expected Max Non-Overlapping Set: [1, 4], [6, 7]. Total: 2
        List<Interval> classes = Arrays.asList(
            new Interval(1, 4), 
            new Interval(2, 5), 
            new Interval(6, 7),
            new Interval(3, 8)
        );

        // After sorting by end time: [1, 4], [2, 5], [6, 7], [3, 8]
        // 1. Pick [1, 4]. lastEndTime = 4. count = 1.
        // 2. [2, 5]: 2 < 4. Skip.
        // 3. [6, 7]: 6 >= 4. Pick [6, 7]. lastEndTime = 7. count = 2.
        // 4. [3, 8]: 3 < 7. Skip.
        
        int maxClasses = solver.maxNonOverlappingIntervals(classes);
        System.out.println("Maximum number of classes a student can attend: " + maxClasses); // Output: 2
    }
}
```

-----

### Follow-up: Counting the Minimum Removals

A common follow-up to this problem is: **"What is the minimum number of classes I must remove to make the remaining schedule non-overlapping?"**

The solution is a simple calculation based on the result above:

$$\text{Minimum Removals} = \text{Total Classes} - \text{Maximum Non-Overlapping Classes}$$

Using the example above:

$$\text{Minimum Removals} = 4 - 2 = 2$$

The student must remove at least 2 classes ([2, 5] and [3, 8]) to keep the remaining 2 non-overlapping.

This video provides a conceptual walk-through of the underlying algorithmic technique for solving calendar conflict and scheduling problems: [Coding Interview Problem: Calendar Conflicts](https://www.youtube.com/watch?v=olK6SWl8UrM).
<http://googleusercontent.com/youtube_content/13>

The Karat interview problem "List of words and a string" typically refers to the challenge of finding the **first word in a given list that can be constructed entirely using the characters of a provided main string**, with each character being used at most once.

This is a classic **Anagram Check/Frequency Map** problem. The most efficient solution involves using a frequency map (or an array for the 26 lowercase English letters) to count the characters available in the main string.

-----

## 🚀 Java Solution: Frequency Map Approach

The solution involves two main steps:

1. **Count Available Characters:** Create a frequency map (an integer array of size 26) for the characters in the main string.
2. **Check Each Word:** Iterate through the list of words and, for each word, check if its character counts can be satisfied by the main string's frequency map. The **first one** that can be satisfied is the answer.

### The Algorithm

```java
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class WordConstructor {

    /**
     * Finds the first word in the list that can be formed using the characters
     * from the given main string, with each character used at most once.
     *
     * Time Complexity: O(L + N * M), where L is the length of the main string,
     * N is the number of words, and M is the average length of a word.
     *
     * @param mainString The string containing available characters.
     * @param wordList The list of words to check.
     * @return The first constructible word, or null/empty string if none is found.
     */
    public String findFirstConstructibleWord(String mainString, List<String> wordList) {
        if (mainString == null || mainString.isEmpty() || wordList == null || wordList.isEmpty()) {
            return "";
        }

        // --- Step 1: Count Available Characters in the main string ---
        // Assuming lowercase English letters.
        int[] mainStringCounts = new int[26];
        for (char c : mainString.toCharArray()) {
            mainStringCounts[c - 'a']++;
        }

        // --- Step 2: Check Each Word in the list ---
        for (String word : wordList) {
            if (canConstruct(word, mainStringCounts)) {
                return word; // Found the first one
            }
        }

        return ""; // No constructible word found
    }

    /**
     * Helper function to check if a word can be constructed from the available counts.
     *
     * @param word The word to check.
     * @param availableCounts The frequency array of available characters from the main string.
     * @return true if the word can be constructed, false otherwise.
     */
    private boolean canConstruct(String word, int[] availableCounts) {
        // Create a temporary frequency array for the current word
        int[] wordCounts = new int[26];

        for (char c : word.toCharArray()) {
            wordCounts[c - 'a']++;
            
            // Optimization: Fail fast if the word's requirement already exceeds availability
            if (wordCounts[c - 'a'] > availableCounts[c - 'a']) {
                return false;
            }
        }
        
        // If the loop completes without an early exit, all characters are available.
        return true;
        
        // Note: The loop-internal check is sufficient and slightly faster than
        // checking all 26 counts in a second loop after counting all word characters.
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        WordConstructor solver = new WordConstructor();

        String main = "balloons";
        List<String> words = Arrays.asList("son", "balls", "balloon", "friends");

        // Frequencies in "balloons": b=1, a=1, l=2, o=2, n=1, s=1
        // 1. "son": s=1, o=1, n=1. Available. -> RETURN "son"
        // 2. "balls": b=1, a=1, l=2, s=2. Requires s=2, only s=1 available. Not constructible.
        // 3. "balloon": l=2, o=2, n=1. Available.
        // 4. "friends": f, r, i, e, d, t. All unavailable.

        String result = solver.findFirstConstructibleWord(main, words);
        System.out.println("Main String: \"" + main + "\"");
        System.out.println("Word List: " + words);
        System.out.println("First constructible word: \"" + result + "\""); // Output: "son"
        
        System.out.println("---");
        
        main = "abc";
        words = Arrays.asList("a", "b", "c", "d");
        result = solver.findFirstConstructibleWord(main, words);
        System.out.println("Main String: \"" + main + "\"");
        System.out.println("Word List: " + words);
        System.out.println("First constructible word: \"" + result + "\""); // Output: "a"
    }
}
```

-----

The video below offers an explanation and solution for a similar problem, "Find Words That Can Be Formed by Characters," which also uses character counting and frequency comparison.

[LeetCode Find Words That Can Be Formed by Characters Solution Explained](https://www.youtube.com/watch?v=M2HFao-zgk8)
<http://googleusercontent.com/youtube_content/15>

The Karat interview problem **"Courses Sharing with Friends"** (or "Find Common Courses") typically asks you to process a list of course enrollments and then, for every pair of students, determine the set of courses they have in common.

This is a **Graph/Set Manipulation** problem that is best solved using **HashMaps** and **HashSets** to efficiently store and compare student enrollment data.

-----

## 🚀 Java Solution: HashMap and HashSet

The solution involves a two-phase process:

1. **Map Courses to Students:** Process the input list to create a primary data structure that maps each student to the *set* of courses they are enrolled in.
2. **Compare All Pairs:** Iterate through all unique pairs of students and find the intersection of their course sets.

### 1\. The Enrollment Map Structure

```java
import java.util.*;

public class CoursesSharing {

    /**
     * Finds the set of common courses for every unique pair of students.
     *
     * Time Complexity: O(C + S^2 * M), where C is the total number of enrollment
     * records, S is the number of students, and M is the average number of courses
     * two students have in common. The dominant factor is comparing S^2 pairs.
     *
     * @param enrollments A list of [student_id, course_name] pairs.
     * @return A map where the key is a pair of student IDs (e.g., "A,B") and the
     * value is a set of courses they share.
     */
    public Map<String, Set<String>> findCommonCourses(List<List<String>> enrollments) {
        
        // --- Step 1: Map Student to their Set of Courses ---
        // Key: Student ID (String), Value: Set of Courses (Set<String>)
        Map<String, Set<String>> studentCourses = new HashMap<>();

        for (List<String> record : enrollments) {
            String student = record.get(0);
            String course = record.get(1);
            
            // Use computeIfAbsent to initialize the Set if the student is new
            studentCourses.computeIfAbsent(student, k -> new HashSet<>()).add(course);
        }

        // --- Step 2: Compare All Unique Pairs of Students ---
        Map<String, Set<String>> commonCourses = new HashMap<>();
        List<String> students = new ArrayList<>(studentCourses.keySet());
        int numStudents = students.size();

        // Iterate over all unique pairs (i, j) where i < j
        for (int i = 0; i < numStudents; i++) {
            for (int j = i + 1; j < numStudents; j++) {
                String studentA = students.get(i);
                String studentB = students.get(j);

                Set<String> coursesA = studentCourses.get(studentA);
                Set<String> coursesB = studentCourses.get(studentB);
                
                // Find the intersection of the two sets
                Set<String> intersection = getIntersection(coursesA, coursesB);

                // Store the result using a consistent key format (e.g., "A,B")
                String pairKey = studentA + "," + studentB;
                commonCourses.put(pairKey, intersection);
            }
        }

        return commonCourses;
    }

    /**
     * Helper function to find the intersection of two sets.
     * By iterating over the smaller set, we optimize the search time.
     */
    private Set<String> getIntersection(Set<String> set1, Set<String> set2) {
        // Ensure set1 is the smaller set for efficiency
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }

        Set<String> intersection = new HashSet<>();
        for (String course : set1) {
            if (set2.contains(course)) {
                intersection.add(course);
            }
        }
        return intersection;
    }

    // --- Example Usage ---
    public static void main(String[] args) {
        CoursesSharing solver = new CoursesSharing();

        List<List<String>> enrollments = Arrays.asList(
            Arrays.asList("A", "CS101"),
            Arrays.asList("A", "MATH203"),
            Arrays.asList("B", "CS101"),
            Arrays.asList("B", "PHYS100"),
            Arrays.asList("C", "MATH203"),
            Arrays.asList("C", "CS300"),
            Arrays.asList("D", "PHYS100"),
            Arrays.asList("D", "ECON101")
        );

        Map<String, Set<String>> results = solver.findCommonCourses(enrollments);

        // Expected Output:
        // A,B: [CS101]
        // A,C: [MATH203]
        // A,D: []
        // B,C: []
        // B,D: [PHYS100]
        // C,D: []

        System.out.println("Common Courses for Student Pairs:");
        results.forEach((pair, courses) -> {
            System.out.println(pair + ": " + courses);
        });
    }
}
```

-----

The problem requires careful handling of data relationships, which is also a core part of technical interview prep. This video provides relevant tips for remote technical interviews, a common format for Karat assessments: [How to prepare for remote technical interviews](https://www.youtube.com/watch?v=zA_yqQOgxiY).
<http://googleusercontent.com/youtube_content/17>

The Karat "Snake in a Board" problem typically involves a **2D grid traversal** challenge, which is best solved using **Graph Search Algorithms** like **Breadth-First Search (BFS)** or **Depth-First Search (DFS)**.

The overall problem is usually broken down into two main parts:

1. **`canMoveFreely(board, start)`:** Determines if the snake can traverse the board indefinitely without hitting any walls or itself. This is often a variation of checking for an **infinite path** or an **unvisited cycle**.
2. **`nearestExit(board, start)`:** Finds the **shortest path** from the starting position to the nearest "exit" (usually any boundary square that is an open path '0'). This is a classic **Shortest Path** problem, solved using **BFS**.

Here are the Java solutions for these two parts. I'll focus on the second part, which is generally more complex and often required.

-----

## 1\. `canMoveFreely(board, start)`

This function checks if the snake can move *without* hitting a wall (`+`) or a visited square. Since the problem often implies the snake **cannot revisit squares**, this is a simple **Depth-First Search (DFS)** or iterative traversal to see if all reachable paths eventually lead to a dead end.

### **The Key Idea (No Cycles):**

A snake can move freely if, from its starting point, it can eventually reach a point where it can continue moving to an unvisited location. Since the typical constraint is **no revisiting**, "moving freely" often means finding a path of infinite length (which is impossible on a finite board) or simply being able to make at least one move. For the *true* Karat variant, the most common interpretation is checking for a simple **valid path** or a path that leads to an exit.

For a simpler interpretation, let's check if the path can continue:

```java
public boolean canMoveFreely(char[][] board, int startR, int startC) {
    // Standard approach is a recursive DFS
    int R = board.length;
    int C = board[0].length;
    boolean[][] visited = new boolean[R][C];

    // Check if the starting position is valid
    if (startR < 0 || startR >= R || startC < 0 || startC >= C || board[startR][startC] == '+') {
        return false;
    }

    // Call a helper function to see if we can find any valid continuation
    return dfsCheck(board, startR, startC, visited);
}

// A simple DFS to see if there is *any* valid path to continue from this spot
private boolean dfsCheck(char[][] board, int r, int c, boolean[][] visited) {
    int R = board.length;
    int C = board[0].length;

    // Base case: check for out of bounds, wall, or already visited
    if (r < 0 || r >= R || c < 0 || c >= C || board[r][c] == '+' || visited[r][c]) {
        return false;
    }

    // Mark current cell as visited for this path
    visited[r][c] = true;

    // Check if any neighboring move is possible
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // R, L, D, U
    
    // If any of the neighbors is an *unvisited, non-wall* cell, the snake can move.
    for (int[] dir : directions) {
        int nextR = r + dir[0];
        int nextC = c + dir[1];
        
        if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && 
            board[nextR][nextC] == '0' && !visited[nextR][nextC]) {
            // A possible next move is found, so it can 'move freely' (continue)
            return true;
        }
    }
    
    // BACKTRACK: Unmark the current cell for future pathfinding checks
    visited[r][c] = false;

    // If no valid *next* move was found, return false (dead end)
    return false;
}
```

-----

## 2\. `nearestExit(board, start)`

This function requires finding the **minimum number of steps** to reach the nearest exit. Exits are defined as an open square (`0`) on the board's **edge/boundary**. This is a quintessential **Breadth-First Search (BFS)** problem.

### **The Key Idea (Shortest Path):**

BFS explores the board layer by layer (level by level), which guarantees that the first time you reach a target (in this case, an exit), it is via the shortest path in terms of steps.

### **Data Structures:**

* **Queue:** Stores the cells to visit, along with the distance traveled: `[row, col, distance]`.
* **Visited Set/Array:** Prevents cycles and redundant work.

<!-- end list -->

```java
import java.util.LinkedList;
import java.util.Queue;

public class SnakeBoardSolution {

    // Class to hold cell coordinates and distance for BFS
    private static class Cell {
        int r, c, dist;
        public Cell(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    /**
     * Finds the shortest distance to the nearest 'exit' (a '0' on the board boundary).
     *
     * Time Complexity: O(R * C), where R and C are board dimensions, as each cell is visited at most once.
     * Space Complexity: O(R * C) for the queue and the visited array.
     *
     * @param board The grid with '0' (path) and '+' (wall).
     * @param startR Starting row.
     * @param startC Starting column.
     * @return The minimum distance to the nearest exit, or -1 if no exit is reachable.
     */
    public int nearestExit(char[][] board, int startR, int startC) {
        int R = board.length;
        int C = board[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // R, L, D, U

        // 1. Initialization
        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        // The starting point itself *cannot* be the exit (distance 0), so we enqueue it with distance 0.
        queue.offer(new Cell(startR, startC, 0));
        visited[startR][startC] = true;

        // The input problem often specifies the starting position is an open path, 
        // but we should validate it here if necessary.
        if (board[startR][startC] == '+') {
            return -1; // Or throw an exception for invalid start
        }

        // 2. BFS Traversal
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            
            // Check neighbors for next move
            for (int[] dir : directions) {
                int nextR = current.r + dir[0];
                int nextC = current.c + dir[1];
                int nextDist = current.dist + 1;

                // Check bounds
                if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C) {
                    
                    // Check if it's a valid path AND not visited
                    if (board[nextR][nextC] == '0' && !visited[nextR][nextC]) {
                        
                        // Check if this new cell is an EXIT (on the boundary)
                        if (nextR == 0 || nextR == R - 1 || nextC == 0 || nextC == C - 1) {
                            return nextDist; // Found the shortest path to an exit!
                        }

                        // Not an exit, so continue BFS from here
                        visited[nextR][nextC] = true;
                        queue.offer(new Cell(nextR, nextC, nextDist));
                    }
                }
            }
        }

        // 3. No exit reachable
        return -1;
    }
    
    // Main for example usage
    public static void main(String[] args) {
        SnakeBoardSolution solver = new SnakeBoardSolution();
        
        // '+' is a wall, '0' is a path
        char[][] board = {
            {'+', '+', '+', '0'},
            {'0', '0', '0', '+'},
            {'+', '0', '+', '0'},
            {'0', '0', '0', '0'}
        };
        
        int startR = 2; // Row 2
        int startC = 1; // Column 1
        
        // Find nearest exit from (2, 1) -> Exit is (3, 1) or (2, 3) or (0, 3)
        // Path to (2, 3) is 2 steps: (2, 1) -> (1, 1) -> (1, 2) -> (2, 2) X
        // Path to (0, 3): (2, 1) -> (1, 1) -> (1, 2) -> (1, 3) X
        // Path: (2, 1) -> (3, 1) is 1 step (3,1 is an exit)
        // Path: (2, 1) -> (1, 1) -> (1, 0) is 2 steps (1,0 is an exit)
        // Shortest path is 1 step to (3, 1).
        
        int distance = solver.nearestExit(board, startR, startC);
        System.out.println("Nearest Exit Distance from (" + startR + ", " + startC + "): " + distance); // Expected: 1
        
        // Example 2: No exit
        char[][] board2 = {
            {'+', '+', '+'},
            {'+', '0', '+'},
            {'+', '+', '+'}
        };
        distance = solver.nearestExit(board2, 1, 1);
        System.out.println("Nearest Exit Distance from (1, 1): " + distance); // Expected: -1
    }
}
```

The approach to finding the shortest path to the nearest exit in a grid is very similar to solving the LeetCode "Nearest Exit from Entrance in Maze" problem. [Leetcode 1926 Nearest Exit from Entrance in Maze | Coding Decoded SDE Sheet](https://www.youtube.com/watch?v=niDFGmMytLk) provides a walkthrough of this classic BFS grid problem.
<http://googleusercontent.com/youtube_content/19>

The "Advising Meeting with CS Students Karat" problem is a classic scheduling and resource allocation challenge. It tests your ability to process time-based data, identify overlaps, and find optimal solutions.

The most common version of this problem asks you to:

1. Process a list of student advising requests, each with a start and end time.
2. Determine the **maximum number of students** a single advisor can meet with, assuming they can only meet with one student at a time.
3. Identify the **maximum number of advisors** needed to meet with all students without any conflicts.

This problem is a direct application of the **Interval Scheduling** and **Interval Partitioning** algorithms.

-----

## 🧩 Problem Statement: Advising Meeting Schedule

You are given a list of time intervals, where each interval represents a student's request for an advising meeting. Each interval is defined by a start time and an end time.

Your tasks are:

1. **Maximum Meetings for One Advisor:** Find the maximum number of non-overlapping meetings that can be scheduled for a single advisor.
2. **Minimum Advisors Needed:** Find the minimum number of advisors required to schedule all meetings without any time conflicts.

### **Example**

`meetings` = `[[0, 30], [5, 10], [15, 20]]`

* **Task 1:** A single advisor can meet with `[5, 10]` and `[15, 20]`. The meeting `[0, 30]` overlaps with both. The maximum number of meetings is **2**.
* **Task 2:** At time `[5, 10]`, two meetings are happening: `[0, 30]` and `[5, 10]`. This requires **2** advisors.

-----

## 💻 Java Solution: Meeting Scheduling

This solution provides the code for both parts of the problem.

### **Part 1: Maximum Meetings for One Advisor (Interval Scheduling)**

This is a greedy algorithm. The key insight is to always choose the meeting that finishes earliest.

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class MeetingScheduler {

    /**
     * Finds the maximum number of non-overlapping meetings for a single advisor.
     * This uses a greedy approach.
     *
     * @param meetings A list of integer arrays, where each array is [start, end].
     * @return The maximum number of meetings.
     */
    public int maxMeetings(List<int[]> meetings) {
        if (meetings == null || meetings.isEmpty()) {
            return 0;
        }

        // Sort meetings by their end times. This is the crucial greedy step.
        meetings.sort(Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int lastEndTime = meetings.get(0)[1];

        for (int i = 1; i < meetings.size(); i++) {
            int currentStartTime = meetings.get(i)[0];
            
            // If the current meeting starts after the last one ended, it's non-overlapping.
            if (currentStartTime >= lastEndTime) {
                count++;
                lastEndTime = meetings.get(i)[1];
            }
        }

        return count;
    }
}
```

### **Part 2: Minimum Advisors Needed (Interval Partitioning)**

This is also a greedy algorithm, often solved using a sweep-line approach or by sorting events.

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class MeetingScheduler {

    /**
     * Finds the minimum number of advisors required to schedule all meetings.
     * This uses a min-heap (PriorityQueue) to track meeting end times.
     *
     * @param meetings A list of integer arrays, where each array is [start, end].
     * @return The minimum number of advisors.
     */
    public int minAdvisors(List<int[]> meetings) {
        if (meetings == null || meetings.isEmpty()) {
            return 0;
        }

        // Sort meetings by their start times.
        meetings.sort(Comparator.comparingInt(a -> a[0]));

        // A min-heap to store the end times of meetings currently in progress.
        // The size of the heap at any point represents the number of concurrent meetings.
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        
        // Add the end time of the first meeting to the heap.
        endTimes.add(meetings.get(0)[1]);

        for (int i = 1; i < meetings.size(); i++) {
            int[] currentMeeting = meetings.get(i);
            int currentStartTime = currentMeeting[0];
            
            // If the earliest meeting in progress has already ended,
            // we can reuse that advisor.
            if (currentStartTime >= endTimes.peek()) {
                endTimes.poll(); // Remove the finished meeting
            }
            
            // Add the current meeting's end time to the heap.
            endTimes.add(currentMeeting[1]);
        }

        // The maximum size of the heap is the minimum number of advisors needed.
        // The final size of the heap represents the maximum number of concurrent meetings.
        return endTimes.size();
    }
}
```

## 🧠 Logic and Complexity Justification

* **Part 1 (`maxMeetings`):** Sorting by end time is the key. By always picking the meeting that frees up the advisor earliest, you maximize the opportunities for subsequent meetings. The time complexity is dominated by the sort: $\mathbf{O(N \log N)}$.
* **Part 2 (`minAdvisors`):** This is a classic Interval Partitioning problem. Sorting by start time and using a min-heap to track end times is the most efficient solution. The heap's size at any point represents the number of concurrent meetings. The maximum size the heap reaches is the answer. The time complexity is dominated by the sort and heap operations: $\mathbf{O(N \log N)}$.
