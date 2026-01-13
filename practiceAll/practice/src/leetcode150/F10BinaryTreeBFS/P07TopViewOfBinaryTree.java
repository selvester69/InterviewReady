package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.TreeMap;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P07TopViewOfBinaryTree {

    static class Pair {
        TreeNode node;
        int hd;
        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static List<Integer> topView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair current = q.poll();
            TreeNode temp = current.node;
            int hd = current.hd;
            if (!map.containsKey(hd)) {
                map.put(hd, temp.val);
            }
            if (temp.left != null) {
                q.add(new Pair(temp.left, hd - 1));
            }
            if (temp.right != null) {
                q.add(new Pair(temp.right, hd + 1));
            }
        }
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Top View of Binary Tree ===\n");

        // Test Case 1: Standard Binary Tree
        // Input: [1,2,3,null,4,null,null,null,5,null,6]
        // Columns: -1:[2], 0:[1], 1:[3], -2:[4], -3:[5], -4:[6]
        // Top View: [6, 5, 4, 2, 1, 3] (Sorted by column index)
        TreeNode case1 = buildTree(new Integer[]{1, 2, 3, 4, null, null, null, 5, null,6, null});
        List<Integer> expected1 = Arrays.asList(6, 5, 4, 2, 1, 3);
        runTest(case1, expected1, "Standard binary tree with deep left growth");

        // Test Case 2: Balanced Tree
        // Input: [1,2,3,4,5,6,7]
        // Columns: -2:[4], -1:[2], 0:[1], 1:[3], 2:[7]
        TreeNode case2 = buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected2 = Arrays.asList(4, 2, 1, 3, 7);
        runTest(case2, expected2, "Balanced binary tree");

        // Test Case 3: Empty Tree
        TreeNode case3 = null;
        List<Integer> expected3 = new ArrayList<>();
        runTest(case3, expected3, "Empty tree (null root)");

        // Test Case 4: Single Node
        TreeNode case4 = buildTree(new Integer[]{1});
        List<Integer> expected4 = Arrays.asList(1);
        runTest(case4, expected4, "Single node tree");

        // Test Case 5: Right-Skewed Tree
        // Input: [1,null,2,null,3,null,4]
        TreeNode case5 = buildTree(new Integer[]{1, null, 2, null, 3, null, 4});
        List<Integer> expected5 = Arrays.asList(1, 2, 3, 4);
        runTest(case5, expected5, "Right-skewed tree");

        // Test Case 6: Overlapping Nodes (Top view only takes the first one encountered at depth)
        // Input: [1,2,3,null,4,5,6]
        // At col 0: 1 is top, 4 and 5 are hidden.
        TreeNode case6 = buildTree(new Integer[]{1, 2, 3, null, 4, 5, null});
        List<Integer> expected6 = Arrays.asList(2, 1, 3);
        runTest(case6, expected6, "Tree with overlapping hidden nodes at column 0");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(TreeNode root, List<Integer> expected, String description) {
        System.out.println("Test Case: " + description);
        List<Integer> result = topView(root);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (Objects.equals(result, expected) ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }

    /**
     * Helper method to build a tree from a LeetCode-style Integer array.
     */
    private static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null || nodes.length == 0) return null;
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