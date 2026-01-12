package leetcode150.F11BinarySearchTree;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P03ValidateBST {

    // brute force way is to inorder tranversal as sorted value and check each value

    // optimised solution to use this technique
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return isValidBSTUtil(root, null, null);
    }

    public boolean isValidBSTUtil(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        return isValidBSTUtil(root.left, min, root.val) && isValidBSTUtil(root.right, root.val, max);
    }

}
