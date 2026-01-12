package leetcode150.F09BinaryTreeGeneral;

public class P20DiameterOfBinaryTree {

    public int height(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // brute force
    public int diameter_brute(TreeNode root) {
        if (root == null)
            return 0;
        int lheight = height(root.left);
        int rheight = height(root.right);

        int ldiameter = diameter_brute(root.left);
        int rdiameter = diameter_brute(root.right);

        return Math.max(lheight + rheight, Math.max(ldiameter, rdiameter));
    }

    int maxDiameter = 0;

    // optimised
    public int diameter(TreeNode root) {
        maxDiameter = 0;
        dfs(root);
        return maxDiameter;
    }

    public int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int lheight = dfs(root.left);
        int rheight = dfs(root.right);

        if (lheight + rheight > maxDiameter) {
            maxDiameter = lheight + rheight;
        }
        return 1 + Math.max(lheight, rheight);
    }
}