package leetcode150.F07Stack;

import java.util.Stack;

public class P06MaxStackUsingPair {
    Stack<int[]> stack;

    P06MaxStackUsingPair() {
        this.stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new int[] { val, val });
        } else {
            int max = Math.max(stack.peek()[1], val);
            stack.push(new int[] { val, max });
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        }
        int pop = stack.pop()[0];
        return pop;
    }

    public int peek() {
        if(stack.isEmpty())
            return -1;
        return stack.peek()[0];
    }
    
    public int getMax() {
        if(stack.isEmpty())
            return -1;
        return stack.peek()[1];
    }
}
