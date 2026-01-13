package leetcode150.F09BinaryTreeGeneral;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path
 * from root to X there are no nodes with a value greater than X.
 * 
 * Return the number of good nodes in the binary tree.
 * 
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * 
 * 
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * 
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 */

// 1448. Count Good Nodes in Binary Tree
public class P26CountGoodNodesInBinaryTree {

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    // this is preorder traversal of binary tree
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