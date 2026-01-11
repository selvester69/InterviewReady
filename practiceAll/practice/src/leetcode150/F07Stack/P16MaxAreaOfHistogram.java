package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

// 84. Largest Rectangle in Histogram
public class P16MaxAreaOfHistogram {

    public static int[] nsr_index(int[] nums) {
        int[] res = new int[nums.length];
        Stack<int[]> stack = new Stack<>();
        int index = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek()[0] >= nums[i]) { 
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[index--] = nums.length;
            } else {
                res[index--] = stack.peek()[1];
            }
            stack.push(new int[] { nums[i], i });
        }
        System.out.println(Arrays.toString(res));
        return res;

    }

    public static int[] nsl_index(int[] nums) {
        int res[] = new int[nums.length];
        Stack<int[]> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[index++] = -1;
            } else {
                res[index++] = stack.peek()[1];
            }
            stack.push(new int[] { nums[i], i });
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    // idea is calculate nsr and nsl
    public static int maxHistogram_bruteForce(int[] nums) {
        int[] nsr = nsr_index(nums);
        int[] nsl = nsl_index(nums);
        int[] res = new int[nums.length];
        int maxArea = 0;
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] * (nsr[i] - nsl[i] - 1);
            maxArea = Math.max(maxArea, res[i]);
        }
        System.out.println(Arrays.toString(res));
        return maxArea;
    }

    // this is similar to above with below optimizaitons
    /**
     * Single Pass: We can calculate the area as we pop elements from the stack.
     * 
     * Monotonic Stack: When we find a height that is shorter than the height at
     * stack.peek(), it means we have found the Nearest Smaller Right (NSR) for that
     * bar. The element below it in the stack is its Nearest Smaller Left (NSL).
     * 
     * Primitive Arrays as Stacks: Replacing java.util.Stack (which is synchronized
     * and slow) with a simple int[] array and a pointer can significantly improve
     * performance on LeetCode.
     */
    public static int maxHistogram_OnePass(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int maxArea = 0;
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            int curr = i == n ? 0 : nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] >= curr) {
                int height = nums[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, width * height);
            }
            stack.push(i);
        }
        return maxArea;
    }
    
    // similar 1 pass Approach
    public static int largestHist(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int max = 0;
        // Phase 1: Processing elements with an identifiable NSE
        for (int i = 0; i < nums.length; i++) {
            while (!st.empty() && nums[st.peek()] > nums[i]) {
                int element = nums[st.peek()];
                st.pop();
                int nse = i;
                int pse = st.empty() ? -1 : st.peek();
                max = Math.max(max, element * (nse - pse - 1));
            }
            st.push(i);
        }

        // Phase 2: Cleanup for elements with no NSE (NSE = arr.length)
        while (!st.empty()) {
            int element = nums[st.peek()];
            st.pop();
            int nse = nums.length;
            int pse = st.empty() ? -1 : st.peek();
            max = Math.max(max, element * (nse - pse - 1));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxHistogram_bruteForce(new int[] { 6, 2, 5, 4, 5, 1, 6 }));
        System.out.println(maxHistogram_OnePass(new int[] { 6, 2, 5, 4, 5, 1, 6 }));
    }

}
