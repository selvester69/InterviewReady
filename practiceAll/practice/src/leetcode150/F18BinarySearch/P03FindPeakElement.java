package leetcode150.F18BinarySearch;

import java.util.Arrays;

public class P03FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Find Peak Element ===\n");

        // Test Case 1: Simple peak in middle (LeetCode Example 1)
        // [1, 2, 3, 1] -> Peak is 3 at index 2
        runTest(new int[] { 1, 2, 3, 1 }, new Integer[] { 2 }, "Simple peak in middle");

        // Test Case 2: Multiple peaks (LeetCode Example 2)
        // [1, 2, 1, 3, 5, 6, 4] -> Peaks are 2 (index 1) or 6 (index 5)
        runTest(new int[] { 1, 2, 1, 3, 5, 6, 4 }, new Integer[] { 1, 5 }, "Multiple peaks");

        // Test Case 3: Single element (Boundary)
        runTest(new int[] { 1 }, new Integer[] { 0 }, "Single element");

        // Test Case 4: Strictly increasing (Peak is at the end)
        runTest(new int[] { 1, 2, 3, 4, 5 }, new Integer[] { 4 }, "Strictly increasing");

        // Test Case 5: Strictly decreasing (Peak is at the start)
        runTest(new int[] { 5, 4, 3, 2, 1 }, new Integer[] { 0 }, "Strictly decreasing");

        // Test Case 6: Two elements, increasing
        runTest(new int[] { 1, 2 }, new Integer[] { 1 }, "Two elements, increasing");

        // Test Case 7: Two elements, decreasing
        runTest(new int[] { 2, 1 }, new Integer[] { 0 }, "Two elements, decreasing");

        // Test Case 8: "Valley" shape (Peaks at both ends)
        runTest(new int[] { 10, 2, 1, 5, 10 }, new Integer[] { 0, 4 }, "Valley shape (peaks at boundaries)");

        // Test Case 9: Plateau-like (Strictly greater than neighbors)
        runTest(new int[] { 1, 5, 2, 6, 3 }, new Integer[] { 1, 3 }, "Alternating peaks");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     * Note: Since there can be multiple valid peak indices,
     * 'expected' is an array of valid answers.
     */
    private static void runTest(int[] nums, Integer[] validIndices, String description) {
        System.out.println("Test Case: " + description);
        int result = findPeakElement(nums);

        boolean passed = false;
        for (int valid : validIndices) {
            if (result == valid) {
                passed = true;
                break;
            }
        }

        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Expected one of: " + Arrays.toString(validIndices));
        System.out.println("Actual Index:    " + result);
        System.out.println("Result:          " + (passed ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }
}