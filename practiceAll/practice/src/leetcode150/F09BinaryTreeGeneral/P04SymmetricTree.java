package leetcode150.F09BinaryTreeGeneral;

public class P04SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return false;
        return isSymmetric(root, root);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null || left.val != right.val)
            return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}