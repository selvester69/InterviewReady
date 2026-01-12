package leetcode150.F09BinaryTreeGeneral;

import java.util.Stack;

public class P12BinarySearchTreeIterator {
    private Stack<TreeNode> stack = new Stack<>();

    public P12BinarySearchTreeIterator(TreeNode root) {
        pushLeft(root);
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}