package dataStructure;

class Queue {
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private Node first;
    private Node last;
    private int length;

    public boolean isEmpty() {
        return this.length == 0;
    }

    public void offer(int val) {// also called as enqueue
        Node newNode = new Node(val);
        if (this.length == 0) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        this.length++;
    }

    public int peek() {
        if (!this.isEmpty()) {
            return this.first.val;
        }
        return -1;
    }

    public int poll() {
        if (this.isEmpty()) {
            return -1;
        }
        Node temp = this.first;
        if (this.length == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.first = this.first.next;
            temp.next = null;
        }
        this.length--;
        return temp.val;
    }

}

public class QueueUsingLinkedList {

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue.poll());
    }

}
