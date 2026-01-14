package leetcode150.F18BinarySearch;

import java.util.Arrays;

public class P06FindMinimumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        if (nums.length == 0)
            return 0;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[start] <= nums[end]) {
                return nums[start];
            } else if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Find Minimum in Rotated Sorted Array ===\n");

        // Test Case 1: Standard rotation (LeetCode Example 1)
        // [3,4,5,1,2] -> Expected: 1
        runTest(new int[] { 3, 4, 5, 1, 2 }, 1, "Standard rotation in the middle");

        // Test Case 2: Rotation at the end (LeetCode Example 2)
        // [4,5,6,7,0,1,2] -> Expected: 0
        runTest(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0, "Larger array, rotation near the end");

        // Test Case 3: No rotation (LeetCode Example 3)
        // [11,13,15,17] -> Expected: 11
        runTest(new int[] { 11, 13, 15, 17 }, 11, "Sorted array with no rotation");

        // Test Case 4: Single element array
        runTest(new int[] { 1 }, 1, "Single element array");

        // Test Case 5: Two elements, rotated
        runTest(new int[] { 2, 1 }, 1, "Two elements, rotated");

        // Test Case 6: Two elements, not rotated
        runTest(new int[] { 1, 2 }, 1, "Two elements, not rotated");

        // Test Case 7: Rotation at index 1
        runTest(new int[] { 5, 1, 2, 3, 4 }, 1, "Rotation immediately after the first element");

        // Test Case 8: Rotation at the very last position
        runTest(new int[] { 2, 3, 4, 5, 1 }, 1, "Minimum is at the very end");

        // Test Case 9: Large values
        runTest(new int[] { 1000, 2000, -5000, -3000, 0 }, -5000, "Array with negative and large values");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(int[] nums, int expected, String description) {
        System.out.println("Test Case: " + description);
        int result = findMin(nums);
        System.out.println("Input: nums = " + Arrays.toString(nums));
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (result == expected ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }
}