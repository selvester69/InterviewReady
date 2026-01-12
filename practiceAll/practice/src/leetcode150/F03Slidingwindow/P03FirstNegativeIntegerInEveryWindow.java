package leetcode150.F03Slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Given an array and a positive integer k, find the first negative integer for
 * each window(contiguous subarray) of size k. If a window does not contain a
 * negative integer, then print 0 for that window.
 * 
 * Examples:
 * 
 * Input: arr[] = [-8, 2, 3, -6, 1] , k = 2
 * Output: [-8, 0, -6, -6]
 * Explanation: First negative integer for each window of size 2
 * [-8, 2] = -8
 * [2, 3]= 0 (does not contain a negative integer)
 * [3, -6] = -6
 * [-6, 10] = -6
 * 
 * Input: arr[] = [12, -1, -7, 8, -15, 30, 16, 28], k = 3
 * Output: [-1, -1, -7, -15, -15, 0]
 * Explanation: First negative integer for each window of size 3
 * [ 12, -1, -7] = -1
 * [-1,-7, 8] = -1
 * [-7, 8, -15] = -7
 * [8, -15, 30] = -15
 * [-15, 30, 16] = -15
 * [30, 16, 28] = 0
 */
public class P03FirstNegativeIntegerInEveryWindow {

    public static int[] firstNegativeIntegerInEveryWindow_Brute(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length - k+1; i++) {
            for (int j = i; j < i + k; j++) {
                if (nums[j] < 0) {
                    res[index] = nums[j];
                    break;
                }
            }
            index++;
        }
        return res;
    }

    public static int[] firstNegativeInEveryWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        int i = 0, j = 0;
        while (j < nums.length) {
            // calculation
            if (nums[j] < 0) {
                queue.add(nums[j]);
            }
            if (j - i + 1 < k) {// find window size
                j++;
            } else if (j - i + 1 == k) {
                // get ans from calculations
                res[index++] = queue.isEmpty() ? 0 : queue.peek();
                // slide the window
                if (!queue.isEmpty() && queue.peek() == nums[i]) {
                    queue.poll();
                }
                i++;
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // [-8, 0, -6, -6]
        System.out.println(Arrays.toString(firstNegativeIntegerInEveryWindow_Brute(new int[] { -8, 2, 3, -6, 1 }, 2)));
        // [-1, -1, -7, -15, -15, 0]
        System.out.println(Arrays.toString(firstNegativeIntegerInEveryWindow_Brute(new int[] { 12, -1, -7, 8, -15, 30,
                16, 28 }, 3)));

        // [-8, 0, -6, -6]
        System.out.println(Arrays.toString(firstNegativeInEveryWindow(new int[] { -8, 2, 3, -6, 1 }, 2)));
        // [-1, -1, -7, -15, -15, 0]
        System.out.println(Arrays.toString(firstNegativeInEveryWindow(new int[] { 12, -1, -7, 8, -15, 30,
                16, 28 }, 3)));
    }
}