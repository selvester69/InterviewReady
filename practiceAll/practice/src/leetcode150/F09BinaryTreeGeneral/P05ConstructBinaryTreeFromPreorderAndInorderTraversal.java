package leetcode150.F09BinaryTreeGeneral;

import java.util.HashMap;
import java.util.Map;

public class P05ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        int[] preIdx = { 0 };
        TreeNode root = helper(preorder, inorderMap, preIdx, 0, inorder.length - 1);
        return root;
    }

    public TreeNode helper(int[] preorder, Map<Integer, Integer> inorderMap, int[] preIdx, int inS, int inE) {
        if (inS > inE) {
            return null;
        }
        int rootData = preorder[preIdx[0]];
        int rootIdx = inorderMap.get(rootData);
        preIdx[0]++;// sub tree root now
        TreeNode root = new TreeNode(rootData);
        root.left = helper(preorder, inorderMap, preIdx, inS, rootIdx - 1);
        root.right = helper(preorder, inorderMap, preIdx, rootIdx + 1, inE);
        return root;
    }

}