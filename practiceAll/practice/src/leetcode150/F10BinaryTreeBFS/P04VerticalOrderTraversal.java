package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of
 * the binary tree.
 * 
 * For each node at position (row, col), its left and right children will be at
 * positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of
 * the tree is at (0, 0).
 * 
 * The vertical order traversal of a binary tree is a list of top-to-bottom
 * orderings for each column index starting from the leftmost column and ending
 * on the rightmost column. There may be multiple nodes in the same row and same
 * column. In such a case, sort these nodes by their values.
 * 
 * Return the vertical order traversal of the binary tree.
 * 
 * 
 */
public class P04VerticalOrderTraversal {

    // using DFS
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<int[]>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        for (List<int[]> vals : map.values()) {
            Collections.sort(vals, (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });
            List<Integer> col = vals.stream().map(a -> a[1]).collect(Collectors.toList());
            res.add(col);
        }
        return res;
    }

    private static void dfs(TreeNode root, int hd, int row, Map<Integer, List<int[]>> map) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(hd, k -> new ArrayList<>()).add(new int[] { row, root.val });
        dfs(root.left, hd - 1, row + 1, map);
        dfs(root.right, hd + 1, row + 1, map);
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Vertical Order Traversal ===\n");

        // Test Case 1: Standard Tree (LeetCode Example 1)
        // Input: [3,9,20,null,null,15,7]
        // Output: [[9],[3,15],[20],[7]]
        TreeNode case1 = buildTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(9),
                Arrays.asList(3, 15),
                Arrays.asList(20),
                Arrays.asList(7));
        runTest(case1, expected1, "Standard binary tree");

        // Test Case 2: Tree with overlapping columns (LeetCode Example 2)
        // Input: [3,9,8,4,0,1,7]
        // Output: [[4],[9],[3,0,1],[8],[7]]
        TreeNode case2 = buildTree(new Integer[] { 3, 9, 8, 4, 0, 1, 7 });
        List<List<Integer>> expected2 = Arrays.asList(
                Arrays.asList(4),
                Arrays.asList(9),
                Arrays.asList(3, 0, 1),
                Arrays.asList(8),
                Arrays.asList(7));
        runTest(case2, expected2, "Tree with multiple overlaps");

        // Test Case 3: Empty Tree
        TreeNode case3 = null;
        List<List<Integer>> expected3 = new ArrayList<>();
        runTest(case3, expected3, "Empty tree (null root)");

        // Test Case 4: Single Node
        TreeNode case4 = buildTree(new Integer[] { 1 });
        List<List<Integer>> expected4 = Arrays.asList(Arrays.asList(1));
        runTest(case4, expected4, "Single node tree");

        // Test Case 5: Left-skewed tree (Negative columns)
        // Input: [1,2,null,3,null,4]
        TreeNode case5 = buildTree(new Integer[] { 1, 2, null, 3, null, 4 });
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(4),
                Arrays.asList(3),
                Arrays.asList(2),
                Arrays.asList(1));
        runTest(case5, expected5, "Left-skewed tree");

        // Test Case 6: Right-skewed tree (Positive columns)
        // Input: [1,null,2,null,3]
        TreeNode case6 = buildTree(new Integer[] { 1, null, 2, null, 3 });
        List<List<Integer>> expected6 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3));
        runTest(case6, expected6, "Right-skewed tree");

        // Test Case 7: Complex Overlap (Depth priority)
        // In Vertical Order, if nodes are in same column, they are ordered by level.
        TreeNode case7 = buildTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
        List<List<Integer>> expected7 = Arrays.asList(
                Arrays.asList(4),
                Arrays.asList(2),
                Arrays.asList(1, 5, 6),
                Arrays.asList(3),
                Arrays.asList(7));
        runTest(case7, expected7, "Balanced tree with column overlaps");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(TreeNode root, List<List<Integer>> expected, String description) {
        System.out.println("Test Case: " + description);
        List<List<Integer>> result = verticalOrder(root);
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
