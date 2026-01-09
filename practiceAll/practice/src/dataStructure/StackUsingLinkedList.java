package dataStructure;

// implement stack using linked list
class Stack {
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    Node head;
    int top;

    Stack() {
        this.head = null;
        this.top = 0;
    }

    private boolean isEmpty() {
        return this.head == null;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.top++;
    }

    public int pop() {
        if (!this.isEmpty()) {
            Node temp = this.head;
            this.head = this.head.next;
            temp.next = null;
            this.top--;
            return temp.val;
        } else {
            return -1;
        }
    }

    public void makeEmpty() {
        this.head = null;
        this.top = 0;
    }

    public void printStack() {
        if (this.top == 0) {
            System.out.println("Stack is empty");
        } else {
            Node temp = this.head;
            while (temp != null) {
                System.out.println(temp.val);
                temp = temp.next;
            }
        }
    }

}

public class StackUsingLinkedList {
    
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.printStack();
    }

}