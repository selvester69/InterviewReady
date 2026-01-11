package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

public class P14NearestSmallerToLeft {

    public static int[] nsl_brute(int[] nums) {
        int res[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    res[i] = nums[j];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] nsl_Stack(int[] nums) {
        int res[] = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[index++] = -1;
            } else {
                res[index++] = stack.peek();
            }
            stack.push(nums[i]);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {
        nsl_brute(new int[] { 4, 5, 2, 10, 8 });
        nsl_Stack(new int[] { 4, 5, 2, 10, 8 });
    }
}
