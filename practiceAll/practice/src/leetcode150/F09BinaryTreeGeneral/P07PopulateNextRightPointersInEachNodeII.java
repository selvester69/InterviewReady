package leetcode150.F09BinaryTreeGeneral;

import java.util.LinkedList;
import java.util.Queue;

public class P07PopulateNextRightPointersInEachNodeII {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    // using DFS solution
    public Node connect(Node root) {
        Node head = root;
        while (head != null) {
            Node dummy = new Node(0), curr = dummy;
            while (head != null) {
                if (head.left != null) {
                    curr.next = head.left;
                    curr = curr.next;
                }
                if (head.right != null) {
                    curr.next = head.right;
                    curr = curr.next;
                }
                head = head.next;
            }
            head = dummy.next;
        }
        return root;
    }

    public Node connect_bfs(Node root) {
        Node head = root;
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Node temp = q.poll();
                size--;
                if (size != 0) { // this is except for the right most node at each step which is null.
                    temp.next = q.peek();
                }
                if (temp.left != null) {
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    q.add(temp.right);
                }
            }
        }
        return root;
    }
}