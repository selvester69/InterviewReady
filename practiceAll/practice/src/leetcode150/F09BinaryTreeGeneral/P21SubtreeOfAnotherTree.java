package leetcode150.F09BinaryTreeGeneral;

public class P21SubtreeOfAnotherTree {

    public boolean areidenticalUtil(TreeNode root, TreeNode subroot) {
        if (root == null && subroot == null)
            return true;
        if (root == null || subroot == null)
            return false;
        return root.val == subroot.val && areidenticalUtil(root.left, subroot.left) &&
                areidenticalUtil(root.right, subroot.right);

    }

    public boolean isSubtree(TreeNode root, TreeNode subroot) {
        if (subroot == null)
            return true;
        if (root == null)
            return false;
        if (areidenticalUtil(root, subroot))
            return true;

        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }
}