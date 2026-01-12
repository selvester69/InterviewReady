package leetcode150.F09BinaryTreeGeneral;

public class P11BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root);
        return maxSum;
    }

    public int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        maxSum = Math.max(maxSum, root.val + left + right);
        return root.val + Math.max(left, right);
    }
}