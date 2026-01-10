package leetcode150.F07Stack;

import java.util.Stack;

public class P03MinStack {
    Stack<Long> stack;
    long min_val = Integer.MAX_VALUE;

    public P03MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        long lg = val;
        if (stack.isEmpty()) {
            stack.push(lg);
            min_val = val;
            return;
        }
        if (lg < min_val) {
            stack.push((2 * lg) - min_val);
            min_val = lg;
        } else {
            stack.push(lg);
        }

    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.peek() < min_val) {
            min_val = 2 * min_val - stack.peek();
            stack.pop();
        } else {
            stack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return -1;
        }
        long top = stack.peek();
        return (int) (min_val > top ? min_val : top);
    }

    public int getMin() {
        return (int) min_val;
    }
}
