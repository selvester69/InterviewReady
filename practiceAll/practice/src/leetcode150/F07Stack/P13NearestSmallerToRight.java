package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

public class P13NearestSmallerToRight {
    public static int[] nsr_brute(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {// we are not calculating till n-1 to include -1 in ans
            res[i] = -1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {// reverse condition from NGR
                    res[i] = nums[j];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] nsr_Stack(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int index = nums.length - 1;
        // since calculating from right to left therfore reverse loop
        for (int i = nums.length - 1; i >= 0; i--) {
            // remove all greater elements from stack
            while (!stack.isEmpty() && stack.peek() >= nums[i]) { // reverse condition from NGR
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[index--] = -1;
            } else {
                res[index--] = stack.peek();
            }
            stack.push(nums[i]);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {
        nsr_brute(new int[] { 4, 5, 2, 10, 8 });
        nsr_Stack(new int[] { 4, 5, 2, 10, 8 });
    }
}
