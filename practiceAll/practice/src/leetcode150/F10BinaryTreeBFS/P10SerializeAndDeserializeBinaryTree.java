package leetcode150.F10BinaryTreeBFS;

import java.util.LinkedList;
import java.util.Queue;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P10SerializeAndDeserializeBinaryTree {

    public String serialize(TreeNode root) {
        if (root == null)
            return "null";
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp == null) {
                sb.append("null,");
                continue;
            }
            sb.append(temp.val + ",");
            q.add(temp.left);
            q.add(temp.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null"))
            return null;
        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (!arr[i].equals("null")) {
                temp.left = new TreeNode(Integer.parseInt(arr[i]));
                q.add(temp.left);
            }
            i++;
            if (i < arr.length && !arr[i].equals("null")) {
                temp.right = new TreeNode(Integer.parseInt(arr[i]));
                q.add(temp.right);
            }
            i++;
        }
        return root;
    }
}