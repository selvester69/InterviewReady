package leetcode150.F03Slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer k. Find the maximum
 * subarray sum of all the subarrays of nums that meet the following conditions:
 * 
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the
 * conditions. If no subarray meets the conditions, return 0.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is
 * repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is
 * repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that
 * meet the conditions
 * Example 2:
 * 
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is
 * repeated.
 * We return 0 because no subarrays meet the conditions.
 * 
 * 
 * Constraints:
 * 
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */

// constraints play major role here.

public class P14MaxSumOfDistinctSubarrwithLengthK {

    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;
        long maxSum = 0;
        int i = 0, j = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (j < nums.length) {
            sum += nums[j];
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                if (map.size() == k) {
                    maxSum = Math.max(sum, maxSum);
                }
                sum -= nums[i];
                if (map.containsKey(nums[i]) && map.get(nums[i]) > 0) {
                    map.put(nums[i], map.get(nums[i]) - 1);
                    if (map.get(nums[i]) == 0) {
                        map.remove(nums[i]);
                    }
                }
                j++;
                i++;
            }
        }
        return maxSum;
    }

    // optimized using frequency array rather than map.
    public long maximumSubarraySum_optimised(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        long currentSum = 0;
        int i = 0, j = 0;
        int[] count = new int[100001];
        int duplicates = 0;

        while (j < n) {
            currentSum += nums[j];
            if (count[nums[j]] > 0) {
                duplicates++;
            }
            count[nums[j]]++;

            if (j - i + 1 > k) {
                count[nums[i]]--;
                if (count[nums[i]] > 0) {
                    duplicates--;
                }
                currentSum -= nums[i];
                i++;
            }

            if (j - i + 1 == k && duplicates == 0) {
                maxSum = Math.max(maxSum, currentSum);
            }

            j++;
        }

        return maxSum;
    }

}
