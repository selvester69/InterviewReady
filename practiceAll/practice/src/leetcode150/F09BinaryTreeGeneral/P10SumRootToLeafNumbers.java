package leetcode150.F09BinaryTreeGeneral;

public class P10SumRootToLeafNumbers {
    int sum = 0;

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int data) {
        if (root == null) {
            return 0;
        }
        data = data * 10 + root.val;
        if (root.left == null && root.right == null)
            return data;
        return dfs(root.left, data) + dfs(root.right, data);

    }
}