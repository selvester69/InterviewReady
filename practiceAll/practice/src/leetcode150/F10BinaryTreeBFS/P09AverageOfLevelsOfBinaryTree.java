package leetcode150.F10BinaryTreeBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode150.F09BinaryTreeGeneral.TreeNode;

public class P09AverageOfLevelsOfBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<Double>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                sum += temp.val;
                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);
            }
            double avg = sum / size;
            res.add(avg);
        }
        return res;
    }
}
