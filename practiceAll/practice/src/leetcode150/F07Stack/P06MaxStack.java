package leetcode150.F07Stack;

import java.util.Stack;

public class P06MaxStack {
    Stack<Integer> stack;
    int max_value = Integer.MIN_VALUE;

    public P06MaxStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            max_value = val;
        } else if (val <= max_value) {
            stack.push(val);
        } else {
            int lg = 2 * val - max_value;
            stack.push(lg);
            max_value = lg;
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int top = stack.pop();
        if (top <= max_value) {
            return top;
        } else {
            int temp = max_value;
            int prev = 2 * max_value - top;
            max_value = prev;
            return temp;
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return this.max_value;
    }

    public int peekMax() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek() <= max_value ? stack.peek() : max_value;
    }

}
