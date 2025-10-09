
## Comprehensive LeetCode Algorithmic Patterns

| Category | Sub-Pattern Name | Description/Focus |
| :--- | :--- | :--- |
| **I. Array & String Manipulation** | | |
| | **Sliding Window** | Finding optimal subarray/substring of fixed or variable size. |
| | **Two Pointers** | Using two indices for linear scans, merging, or finding pairs/triplets. |
| | **Fast & Slow Pointers** | Detecting cycles, finding middle/start of cycles in linked lists. |
| | **Prefix Sum/Product** | Pre-calculating cumulative sums/products for $O(1)$ range queries. |
| | **Monotonic Stack/Queue** | Finding the Next Greater/Smaller Element in linear time. |
| | **Matrix Traversal** | Solving problems on 2D arrays (e.g., Island problems, Spiral Traversal). |
| | **Trie (Prefix Tree)** | Storing strings for efficient prefix search and XOR problems. |
| **II. Greedy Algorithms** | | |
| | **Interval Merging & Scheduling** | Combining overlapping intervals or optimizing task assignments. |
| | **Minimum Jumps & Reachability** | Minimizing steps to reach a target (Jump Game). |
| | **Profitable Stock Trading** | Maximizing profit from sequential transactions. |
| | **Complete Gas Circuit** | Finding a starting point for a circular traversal. |
| | **Efficient Task Scheduling** | Optimizing task execution based on cooldowns or priorities. |
| | **Smart Sorting & Assignment** | Using a locally optimal, sorted choice to find a global optimum (e.g., Assign Cookies). |
| **III. Search & Recursion** | | |
| | **Backtracking** | Exhaustive search by trying and undoing choices. |
| | **- Subsets/Combinations** | Generating all sub-sequences or sets. |
| | **- Permutations** | Generating all unique orderings. |
| | **- Combination Sum** | Finding all number combinations that equal a target. |
| | **- Valid Parentheses** | Generating structurally correct bracket sequences. |
| | **- Word Search & Pathfinding** | DFS-based search in a grid. |
| | **- Solving Puzzles (Constraints)** | Sudoku Solver, N-Queens. |
| | **- Palindrome Partitioning** | Decomposing a string into palindromic pieces. |
| | **Binary Search** | Efficiently finding a value or optimal answer in a sorted/monotonic space. |
| **IV. Dynamic Programming (DP)** | | |
| | **DP - Optimization/Counting** | Solving by breaking into overlapping subproblems (e.g., Knapsack, Longest Common Subsequence). |
| | **DP - State Compression** | Using bitmasks to represent subsets or states efficiently. |
| **V. Data Structure Specific** | | |
| | **Two Heaps** | Maintaining two sorted halves to find the median of a data stream. |
| | **Top K Elements** | Using a Min/Max Heap to find the $K$ largest/smallest items. |
| | **K-way Merge** | Using a Min Heap to efficiently merge $K$ sorted lists or arrays. |
| | **Cycle Sort** | Placing elements at their correct index to find missing/duplicate numbers in a range. |
| | **Union Find (Disjoint Set Union)** | Managing connected components/sets (e.g., Graph connectivity, Kruskal's MST). |
| **VI. Tree Traversals** | | |
| | **Breadth-First Search (BFS)** | Level-order traversal for shortest path or level properties. |
| | **Depth-First Search (DFS) - Preorder** | Root $\to$ Left $\to$ Right (Copying, Serialization). |
| | **Depth-First Search (DFS) - Inorder** | Left $\to$ Root $\to$ Right (BST properties, Sorted Output). |
| | **Depth-First Search (DFS) - Postorder** | Left $\to$ Right $\to$ Root (Cleanup, Height/Depth calculation). |
| | **Lowest Common Ancestor (LCA)** | Finding the shared ancestor of two nodes. |
| | **Tree Serialization & Recognition** | Converting a tree to a string/array and back. |
| **VII. Graph Algorithms** | | |
| | **Topological Sort** | Linear ordering of vertices in a Directed Acyclic Graph (DAG) based on dependencies. |
| | **Shortest Path Algorithms** | Dijkstra's, Bellman-Ford, Floyd-Warshall. |
| | **Minimum Spanning Tree (MST)** | Kruskal's, Prim's (for weighted graph connectivity). |
| | **Strongly Connected Components** | Identifying maximal connected subgraphs in a directed graph. |
| **VIII. Mathematical & Specialized** | | |
| | **Bit Manipulation** | Using bitwise operators for fast calculations and integer property analysis. |
| | **Math & Combinatorics** | Problems involving number theory, modular arithmetic, or counting principles. |
| | **Reservoir Sampling** | Selecting a random sample from a large, continuous stream of data. |


Greedy

1. Interval Merging & Scheduling

- Merge Intervals
- Insert Interval
- Employee Free Time
- Interval List Intersections
- Divide Intervals Into Minimum Number of Groups

2. Minimum Jumps & Reachability

- Jump Game II
- Jump Game

3. Profitable Stock Trading

- Best Time to Buy and Sell Stock
- Best Time to Buy and Sell Stock II

4. Complete Gas Circuit

- Gas Station
- Maximize the Topmost Element After K Moves

5. Efficient Task Scheduling

- Task Scheduler
- Reorganize String
- Distant Barcodes

6. Smart Sorting & Assignment

- Assign Cookies
- Candy
- Queue Reconstruction by Height
- Two City Scheduling

○ Backtracking
7. Subsets by Including/Excluding Choices

- Letter Combinations of a Phone Number
- Combinations
- Subsets
- Subsets II

8. All Possible Permutations

- Next Permutation
- Permutations
- Permutation Sequence

9. Finding All Combinations That Add Up

- Combination Sum
- Combination Sum II

10. Generating Valid Parentheses

- Generate Parentheses
- Remove Invalid Parentheses

11. Word Search & Pathfinding in Grids

- Word Search
- Word Search II
- Check if Word Can Be Placed In Crossword

12. Solving Puzzles & Constraints

- Sudoku Solver
- N-Queens

13. Palindrome Partitioning

- Palindrome Partitioning
- Palindrome Partitioning II
- Pseudo-Palindromic Paths in a Binary Tree

○ Tree Traversals

14. Breadth-First Search (Level Order)

- Binary Tree Level Order Traversal
- Binary Tree Zigzag Level Order Traversal
- Binary Tree Right Side View
- Find Largest Value in Each Tree Row
- Maximum Level Sum of a Binary Tree

15. Preorder Depth-First Search

- Same Tree
- Symmetric Tree
- Construct Binary Tree from Preorder and Inorder
- Flatten Binary Tree to Linked List
- Invert Binary Tree
- Binary Tree Paths
- Smallest String Starting From Leaf

16. Inorder Depth-First Search

- Binary Tree Inorder Traversal
- Validate Binary Search Tree
- Binary Search Tree Iterator
- Kth Smallest Element in a BST
- Find Mode in Binary Search Tree
- Minimum Absolute Difference in BST

17. Postorder Depth-First Search

- Maximum Depth of Binary Tree
- Balanced Binary Tree
- Binary Tree Maximum Path Sum
- Binary Tree Postorder Traversal
- House Robber III
- Find Leaves of Binary Tree
- Diameter of Binary Tree
- All Nodes Distance K in Binary Tree
- Delete Nodes And Return Forest
- Height of Binary Tree After Subtree Removal

18. Finding the Lowest Common Ancestor

- Lowest Common Ancestor of a Binary Search Tree
- Lowest Common Ancestor of a Binary Tree

19. Tree Serialization & Structure Recognition

- Serialize and Deserialize Binary Tree
- Subtree of Another Tree
- Find Duplicate Subtrees
