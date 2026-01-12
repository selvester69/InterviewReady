package leetcode150.F09BinaryTreeGeneral;

import java.util.HashMap;
import java.util.Map;

public class P06ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        int[] postIndex = new int[] { n - 1 };
        return helper(postorder, map, postIndex, 0, n - 1);
    }

    public TreeNode helper(int[] postorder, Map<Integer, Integer> map, int[] postIndex,
            int inS, int inE) {
        if (inS > inE)
            return null;
        int rootData = postorder[postIndex[0]];
        int index = map.get(rootData);
        TreeNode root = new TreeNode(rootData);
        postIndex[0]--;
        root.right = helper(postorder, map, postIndex, index + 1, inE);
        root.left = helper(postorder, map, postIndex, inS, index - 1);
        return root;
    }
}