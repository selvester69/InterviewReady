package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

public class P15StockspanProblem {

    // idea is to solve it using NearestGreater to Left(NGL)
    public static int[] stockSpan_bruteForce(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 1;
            for (int j = i - 1; j >= 0 && nums[j] <= nums[i]; j--) {
                count++;
            }
            res[i] = count;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] stockSpan_Stack(int[] nums) {
        int[] res = new int[nums.length];
        Stack<int[]> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] <= nums[i]) {// remove all smaller elements
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[index++] = -1;
            } else {
                res[index++] = stack.peek()[1];
            }
            stack.push(new int[] { nums[i], i });
        }
        // to the final output calcualte res[i] = i - res[i];
        for (int i = 0; i < nums.length; i++) {
            res[i] = i - res[i];
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {
        stockSpan_bruteForce(new int[] { 100, 80, 60, 120 });// output: [1, 1, 1, 4]
        stockSpan_Stack(new int[] { 100, 80, 60, 120 });// output: [1, 1, 1, 4]
    }

    public static void print2DArr(int[][] arr) {
        for (int i[] : arr) {
            System.out.println(i[0] + " " + i[1]);
        }
    }
}
