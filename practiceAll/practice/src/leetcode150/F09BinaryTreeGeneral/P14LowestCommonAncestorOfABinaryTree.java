package leetcode150.F09BinaryTreeGeneral;

public class P14LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public TreeNode lowestCommonAncestor_Another(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (p == root || q == root) {
            return root;
        }
        // we have to search recursively in both left and right
        // there are 4 possiblities
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 1. p and q not present in both left and right
        if (left == null && right == null)
            return null;
        // 2. p and q present in right
        else if (right != null && left == null) {
            return right;
        }
        // 3. p and q present in left subtree
        else if (left != null && right == null) {
            return left;
        }
        // 4. p and q are present in left and right or viceversa
        else {
            return root;
        }
    }
}