package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P01LevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                level.add(temp.val);
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            res.add(level);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Binary Tree Level Order Traversal ===\n");

        // Test Case 1: Standard binary tree (LeetCode Example 1)
        // Input: root = [3,9,20,null,null,15,7]
        TreeNode case1 = buildTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(9, 20),
                Arrays.asList(15, 7));
        runTest(case1, expected1, "Standard binary tree (Example 1)");

        // Test Case 2: Single node (LeetCode Example 2)
        // Input: root = [1]
        TreeNode case2 = buildTree(new Integer[] { 1 });
        List<List<Integer>> expected2 = Arrays.asList(Arrays.asList(1));
        runTest(case2, expected2, "Single node tree");

        // Test Case 3: Empty tree (LeetCode Example 3)
        // Input: root = []
        TreeNode case3 = null;
        List<List<Integer>> expected3 = new ArrayList<>();
        runTest(case3, expected3, "Empty tree (null root)");

        // Test Case 4: Left-skewed tree
        // Input: root = [1,2,null,3,null,4]
        TreeNode case4 = buildTree(new Integer[] { 1, 2, null, 3, null, 4 });
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4));
        runTest(case4, expected4, "Left-skewed tree");

        // Test Case 5: Right-skewed tree
        // Input: root = [1,null,2,null,3,null,4]
        TreeNode case5 = buildTree(new Integer[] { 1, null, 2, null, 3, null, 4 });
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4));
        runTest(case5, expected5, "Right-skewed tree");

        // Test Case 6: Full binary tree (2 levels)
        // Input: root = [1,2,3]
        TreeNode case6 = buildTree(new Integer[] { 1, 2, 3 });
        List<List<Integer>> expected6 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3));
        runTest(case6, expected6, "Full binary tree (2 levels)");

        // Test Case 7: Larger balanced tree
        // Input: root = [1,2,3,4,5,6,7]
        TreeNode case7 = buildTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        List<List<Integer>> expected7 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6, 7));
        runTest(case7, expected7, "Larger balanced tree");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(TreeNode root, List<List<Integer>> expected, String description) {
        System.out.println("Test Case: " + description);
        List<List<Integer>> result = levelOrder(root);
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
            if (nodes[i] != null) {
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
