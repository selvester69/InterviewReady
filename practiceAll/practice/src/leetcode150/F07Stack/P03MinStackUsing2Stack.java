package leetcode150.F07Stack;

import java.util.Stack;

public class P03MinStackUsing2Stack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    public P03MinStackUsing2Stack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            minStack.push(val);
            return;
        }
        if (val < stack.peek()) {
            minStack.push(val);
            stack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int val = stack.pop();
            if (val == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
