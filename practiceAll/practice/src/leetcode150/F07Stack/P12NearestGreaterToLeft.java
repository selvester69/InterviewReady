package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

public class P12NearestGreaterToLeft {

    public static int[] ngl_brute(int[] nums) {
        int res[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = i - 1; j >= 0; j--) { // reverse the middle loop from previous NGR
                if (nums[j] > nums[i]) {
                    res[i] = nums[j];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] ngl_Stack(int[] nums) {
        int res[] = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        // since we have to calc from left to right using forward loop for stack
        for (int i = 0; i < nums.length; i++) {// reverse loop from NGR
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {// keep condition same as NGR
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
        ngl_brute(new int[] { 1, 3, 2, 4 });
        ngl_Stack(new int[] { 1, 3, 2, 4 });
        System.out.println(Arrays.toString(new int[] { -1, -1, 3, -1 }));
    }

}
