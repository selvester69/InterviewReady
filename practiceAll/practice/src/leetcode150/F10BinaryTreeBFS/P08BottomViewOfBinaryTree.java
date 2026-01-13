package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.TreeMap;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P08BottomViewOfBinaryTree {

    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    // level order traversal
    // at each level calculate horizontal and vertical distance
    // for same row max of hd is the ans
    public static List<Integer> bottomView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                int hd = pair.hd;
                TreeNode temp = pair.node;

                map.put(hd, temp.val);

                if (temp.left != null) {
                    q.add(new Pair(temp.left, pair.hd - 1));
                }
                if (temp.right != null) {
                    q.add(new Pair(temp.right, pair.hd + 1));
                }
            }
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Bottom View of Binary Tree ===\n");

        // Test Case 1: Standard Tree (LeetCode style)
        // Input: [20,8,22,5,3,null,25,null,null,10,14]
        // Bottom View: [5, 10, 3, 14, 25]
        TreeNode case1 = buildTree(new Integer[] { 20, 8, 22, 5, 3, null, 25, null, null, 10, 14 });
        List<Integer> expected1 = Arrays.asList(5, 10, 3, 14, 25);
        runTest(case1, expected1, "Standard binary tree");

        // Test Case 2: Balanced Tree
        // Input: [1,2,3,4,5,6,7]
        // Bottom View: [4, 2, 6, 3, 7] (Note: 6 and 5 are both col 0, 6 is bottom-most)
        TreeNode case2 = buildTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        List<Integer> expected2 = Arrays.asList(4, 2, 6, 3, 7);
        runTest(case2, expected2, "Balanced tree with overlaps");

        // Test Case 3: Empty Tree
        TreeNode case3 = null;
        List<Integer> expected3 = new ArrayList<>();
        runTest(case3, expected3, "Empty tree (null root)");

        // Test Case 4: Single Node
        TreeNode case4 = buildTree(new Integer[] { 1 });
        List<Integer> expected4 = Arrays.asList(1);
        runTest(case4, expected4, "Single node tree");

        // Test Case 5: Left-Skewed Tree
        TreeNode case5 = buildTree(new Integer[] { 1, 2, null, 3, null, 4 });
        List<Integer> expected5 = Arrays.asList(4, 3, 2, 1);
        runTest(case5, expected5, "Left-skewed tree");

        // Test Case 6: Right-Skewed Tree
        TreeNode case6 = buildTree(new Integer[] { 1, null, 2, null, 3 });
        List<Integer> expected6 = Arrays.asList(1, 2, 3);
        runTest(case6, expected6, "Right-skewed tree");

        // Test Case 7: Complex Shadowing
        // Node 10 is at col 0, Level 2. Node 3 is at col 0, Level 1. 10 replaces 3.
        TreeNode case7 = buildTree(new Integer[] { 1, 2, 3, null, null, 10, null });
        List<Integer> expected7 = Arrays.asList(2, 10, 3);
        runTest(case7, expected7, "Complex shadowing at column 0");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(TreeNode root, List<Integer> expected, String description) {
        System.out.println("Test Case: " + description);
        List<Integer> result = bottomView(root);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (Objects.equals(result, expected) ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }

    /**
     * Helper method to build a tree from a LeetCode-style Integer array.
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nodes[0]);
        queue.add(root);
        int i = 1;
        while (i < nodes.length) {
            TreeNode curr = queue.poll();
            if (i < nodes.length && nodes[i] != null) {
                curr.left = new TreeNode(nodes[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < nodes.length && nodes[i] != null) {
                curr.right = new TreeNode(nodes[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }
}