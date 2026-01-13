package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P06LeftSideViewOfBinaryTree {

    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if (i == 0) {// add first element in queue at each level to get left side view.
                    res.add(temp.val);
                }
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Left Side View ===\n");

        // Test Case 1: Standard Tree
        // Input: [1,2,3,null,5,null,4]
        // Output: [1, 2, 5]
        TreeNode case1 = buildTree(new Integer[] { 1, 2, 3, null, 5, null, 4 });
        List<Integer> expected1 = Arrays.asList(1, 2, 5);
        runTest(case1, expected1, "Standard binary tree");

        // Test Case 2: Right-skewed tree (Left view still sees the right nodes)
        // Input: [1,null,2,null,3]
        // Output: [1, 2, 3]
        TreeNode case2 = buildTree(new Integer[] { 1, null, 2, null, 3 });
        List<Integer> expected2 = Arrays.asList(1, 2, 3);
        runTest(case2, expected2, "Right-skewed tree");

        // Test Case 3: Empty Tree
        TreeNode case3 = null;
        List<Integer> expected3 = new ArrayList<>();
        runTest(case3, expected3, "Empty tree (null root)");

        // Test Case 4: Single Node
        TreeNode case4 = buildTree(new Integer[] { 1 });
        List<Integer> expected4 = Arrays.asList(1);
        runTest(case4, expected4, "Single node tree");

        // Test Case 5: Complex Tree (Left side hidden by right side depth)
        // Level 3 has a right-most child that is the only node at that depth
        TreeNode case5 = buildTree(new Integer[] { 1, 2, 3, 4, null, null, null, 5 });
        List<Integer> expected5 = Arrays.asList(1, 2, 4, 5);
        runTest(case5, expected5, "Tree where deeper levels are only on the right");

        // Test Case 6: Full Balanced Tree
        TreeNode case6 = buildTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        List<Integer> expected6 = Arrays.asList(1, 2, 4);
        runTest(case6, expected6, "Full balanced tree");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(TreeNode root, List<Integer> expected, String description) {
        System.out.println("Test Case: " + description);
        List<Integer> result = leftSideView(root);
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