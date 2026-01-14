package leetcode150.F18BinarySearch;

import java.util.Arrays;

public class P04SearchInRotatedSortedArray {

    // 4, 5, 6, 7, 0, 1, 2
    public static int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;

        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] <= nums[end]) {// right side is sorted
                // check is which side element is present
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {// left side is sorted.
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Search in Rotated Sorted Array ===\n");

        // Test Case 1: Standard rotated array, target in left half (LeetCode Example 1)
        // [4,5,6,7,0,1,2], target = 0 -> Expected index 4
        runTest(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0, 4, "Target in the right half of pivot");

        // Test Case 2: Target not in array (LeetCode Example 2)
        runTest(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 3, -1, "Target does not exist");

        // Test Case 3: Empty/Single element array, target missing (LeetCode Example 3)
        runTest(new int[] { 1 }, 0, -1, "Single element, target missing");

        // Test Case 4: Single element array, target found
        runTest(new int[] { 1 }, 1, 0, "Single element, target matches");

        // Test Case 5: Target at the pivot point (Min element)
        runTest(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0, 4, "Target is the minimum element");

        // Test Case 6: Target at the max element (Before pivot)
        runTest(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 7, 3, "Target is the maximum element");

        // Test Case 7: Array not rotated (Pivot is at index 0)
        runTest(new int[] { 1, 2, 3, 4, 5 }, 3, 2, "Array is not rotated");

        // Test Case 8: Small rotation (Pivot at index 1)
        runTest(new int[] { 5, 1, 2, 3, 4 }, 1, 1, "Rotation at index 1, target is min");

        // Test Case 9: Large rotation
        runTest(new int[] { 2, 3, 4, 5, 1 }, 5, 3, "Target is max, rotation at end");

        // Test Case 10: Two elements
        runTest(new int[] { 3, 1 }, 1, 1, "Two elements, rotated");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(int[] nums, int target, int expected, String description) {
        System.out.println("Test Case: " + description);
        int result = search(nums, target);
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (result == expected ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }
}