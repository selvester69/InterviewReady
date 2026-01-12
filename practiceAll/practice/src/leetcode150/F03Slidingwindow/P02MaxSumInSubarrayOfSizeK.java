package leetcode150.F03Slidingwindow;

/**
 * Given an array of integers arr[] and an integer k, find the maximum possible
 * sum among all contiguous subarrays of size exactly k.
 * A subarray is a sequence of consecutive elements from the original array.
 * Return the maximum sum that can be obtained from any such subarray of length
 * k.
 * 
 * Examples:
 * 
 * Input : arr[] = [100, 200, 300, 400], k = 2
 * Output : 700
 * Explanation: We get maximum sum by adding subarray [300,400] of size 2
 * 
 * Input : arr[] = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4
 * Output : 39
 * Explanation: We get maximum sum by adding subarray [4, 2, 10, 23] of size 4.
 * 
 * Input : arr[] = [2, 3], k = 1
 * Output : 3
 * Explanation: The subarrays of size 1 are [2] and [3]. The maximum sum is 3.
 */
public class P02MaxSumInSubarrayOfSizeK {

    public static int maxSumInKWindow_Brute(int[] nums, int k) {
        int maxSum = 0;
        for (int i = 0; i < nums.length - k+1; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static int maxSumInKWindow(int[] nums, int k) {
        int i = 0, j = 0, sum = 0, maxSum = 0;
        while (j < nums.length) {
            // calculation
            sum += nums[j];
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // ans from calc
                maxSum = Math.max(maxSum, sum);
                // slide the window
                sum -= nums[i];
                i++;
                j++;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumInKWindow_Brute(new int[] { 100, 200, 300, 400 }, 2));
        System.out.println(maxSumInKWindow_Brute(new int[] { 1, 4, 2, 10, 23, 3, 1, 0, 20 }, 4));
        System.out.println(maxSumInKWindow_Brute(new int[] { 2, 3 }, 1));

        System.out.println(maxSumInKWindow(new int[] { 100, 200, 300, 400 }, 2));
        System.out.println(maxSumInKWindow(new int[] { 1, 4, 2, 10, 23, 3, 1, 0, 20 }, 4));
        System.out.println(maxSumInKWindow(new int[] { 2, 3 }, 1));
    }
}