package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P03BInaryTreeZigZagLevelOrderTraversal {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int direction = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if (direction == 1) {
                    level.add(temp.val);
                } else {
                    level.add(0, temp.val);
                }
                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
            res.add(level);
            direction = direction * -1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Zigzag Level Order Traversal ===\n");

        // Test Case 1: Standard Tree (LeetCode Example 1)
        // Level 0: [3] (L->R)
        // Level 1: [20, 9] (R->L)
        // Level 2: [15, 7] (L->R)
        TreeNode case1 = buildTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(20, 9),
                Arrays.asList(15, 7));
        runTest(case1, expected1, "Standard binary tree (Example 1)");

        // Test Case 2: Single Node
        TreeNode case2 = buildTree(new Integer[] { 1 });
        List<List<Integer>> expected2 = Arrays.asList(Arrays.asList(1));
        runTest(case2, expected2, "Single node tree");

        // Test Case 3: Empty Tree
        TreeNode case3 = null;
        List<List<Integer>> expected3 = new ArrayList<>();
        runTest(case3, expected3, "Empty tree (null root)");

        // Test Case 4: Perfectly Balanced 3-Level Tree
        // Level 0: [1] (L->R)
        // Level 1: [3, 2] (R->L)
        // Level 2: [4, 5, 6, 7] (L->R)
        TreeNode case4 = buildTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(3, 2),
                Arrays.asList(4, 5, 6, 7));
        runTest(case4, expected4, "Perfectly balanced 3-level tree");

        // Test Case 5: Left-Skewed Tree
        TreeNode case5 = buildTree(new Integer[] { 1, 2, null, 3, null, 4 });
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4));
        runTest(case5, expected5, "Left-skewed tree (alternating single nodes)");

        // Test Case 6: Full tree with large values
        TreeNode case6 = buildTree(new Integer[] { 100, 50, 150, 25, 75, 125, 175 });
        List<List<Integer>> expected6 = Arrays.asList(
                Arrays.asList(100),
                Arrays.asList(150, 50),
                Arrays.asList(25, 75, 125, 175));
        runTest(case6, expected6, "Full tree with large values");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(TreeNode root, List<List<Integer>> expected, String description) {
        System.out.println("Test Case: " + description);
        List<List<Integer>> result = zigzagLevelOrder(root);
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
