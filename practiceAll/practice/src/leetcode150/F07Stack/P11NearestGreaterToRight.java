package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

public class P11NearestGreaterToRight {

    public static int[] NGRBruteForce(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {// we are not calculating till n-1 to include -1 in ans
            res[i] = -1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] ngr(int[] nums) {
        int[] res = new int[nums.length];
        int index = nums.length - 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                res[index--] = -1;
            } else if (!stack.isEmpty() && stack.peek() > nums[i]) {
                res[index--] = stack.peek();
            } else if (!stack.isEmpty() && stack.peek() <= nums[i]) {
                while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    res[index--] = -1;
                } else {
                    res[index--] = stack.peek();
                }
            }
            stack.push(nums[i]);
        }
        System.out.println(Arrays.toString(res));
        return res;

    }

    public static int[] ngr_optimized(int[] nums) {
        int[] res = new int[nums.length];
        int index = nums.length - 1;
        Stack<Integer> stack = new Stack<>();
        // since we have to calc from right to left using forward loop for stack
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
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
        NGRBruteForce(new int[] { 1, 3, 2, 4 });
        ngr(new int[] { 1, 3, 2, 4 });
        ngr_optimized(new int[] { 1, 3, 2, 4 });
    }
}
