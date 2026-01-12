package leetcode150.F09BinaryTreeGeneral;

public class P13CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // another approach
    public int countNodes_AnotherApproach(TreeNode root) {
        if (root == null)
            return 0;
        int left = leftDepth(root.left);
        int right = rightDepth(root.right);
        if (left == right)
            return (1 << left) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int leftDepth(TreeNode node) {
        int d = 0;
        while (node != null) {
            d++;
            node = node.left;
        }
        return d;
    }

    public int rightDepth(TreeNode node) {
        int d = 0;
        while (node != null) {
            d++;
            node = node.right;
        }
        return d;
    }
}