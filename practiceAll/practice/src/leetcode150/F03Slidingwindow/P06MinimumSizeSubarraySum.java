package leetcode150.F03Slidingwindow;

/**
 * **************************
 * variable size sliding window starts
 * **************************
 */

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to
 * target. If there is no such subarray, return 0 instead.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem
 * constraint.
 * Example 2:
 * 
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * 
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * 
 * 
 * Constraints:
 * 
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 
 */
public class P06MinimumSizeSubarraySum {

    public static int minSubArrayLen_brute(int target, int[] nums) {
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minSum = Math.min(minSum, j - i + 1);
                    break;
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? 0 : minSum;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int minSum = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int sum = 0;
        while (j < nums.length) {
            // calculation
            sum += nums[j];
            if (sum >= target) {
                minSum = Math.min(minSum, j - i + 1);
                while (i <= j && sum >= target) {
                    minSum = Math.min(minSum, j - i + 1);
                    sum -= nums[i++];
                }
            }
            j++;
        }
        return minSum == Integer.MAX_VALUE ? 0 : minSum;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen_brute(7, new int[] { 2, 3, 1, 2, 4, 3 }));
        System.out.println(minSubArrayLen_brute(4, new int[] { 1, 4, 4 }));
        System.out.println(minSubArrayLen_brute(11, new int[] { 1, 1, 1, 1, 1, 1, 1, 1 }));

        System.out.println(minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }));
        System.out.println(minSubArrayLen(4, new int[] { 1, 4, 4 }));
        System.out.println(minSubArrayLen(11, new int[] { 1, 1, 1, 1, 1, 1, 1, 1 }));
    }
}