package leetcode150.F09BinaryTreeGeneral;

public class P08FlattenBinaryTreeToLinkedList {

    // solving using dfs
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode left = root.left;
                // get rightmost node of left subtree
                while (left.right != null) {
                    left = left.right;
                }
                left.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}