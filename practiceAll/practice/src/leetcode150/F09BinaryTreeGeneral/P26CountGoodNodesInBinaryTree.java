package leetcode150.F09BinaryTreeGeneral;

public class P26CountGoodNodesInBinaryTree {

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    int dfs(TreeNode node, int maxVal) {
        if (node == null)
            return 0;

        int res = node.val >= maxVal ? 1 : 0;
        maxVal = Math.max(maxVal, node.val);
        res += dfs(node.left, maxVal);
        res += dfs(node.right, maxVal);
        return res;
    }
}