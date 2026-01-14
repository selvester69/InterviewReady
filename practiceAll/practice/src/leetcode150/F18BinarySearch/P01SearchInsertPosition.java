package leetcode150.F18BinarySearch;

import java.util.Arrays;

// after the loop in bianry search is complete 
// start will be at the position where next greater element is present
// which in turn would be the position of curent target if present 
public class P01SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        // edge cases
        if (target < nums[start])
            return start - 1;
        if (target > nums[end])
            return end + 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Search Insert Position ===\n");

        // Test Case 1: Target exists in the middle (LeetCode Example 1)
        runTest(new int[] { 1, 3, 5, 6 }, 5, 2, "Target exists in the middle");

        // Test Case 2: Target does not exist, insert in middle (LeetCode Example 2)
        runTest(new int[] { 1, 3, 5, 6 }, 2, 1, "Target missing, insert in middle");

        // Test Case 3: Target does not exist, insert at end (LeetCode Example 3)
        runTest(new int[] { 1, 3, 5, 6 }, 7, 4, "Target missing, insert at end");

        // Test Case 4: Target does not exist, insert at beginning
        runTest(new int[] { 1, 3, 5, 6 }, 0, 0, "Target missing, insert at beginning");

        // Test Case 5: Single element array, target exists
        runTest(new int[] { 1 }, 1, 0, "Single element, target matches");

        // Test Case 6: Single element array, target smaller
        runTest(new int[] { 5 }, 2, 0, "Single element, target smaller");

        // Test Case 7: Single element array, target larger
        runTest(new int[] { 5 }, 10, 1, "Single element, target larger");

        // Test Case 8: Large gap between numbers
        runTest(new int[] { 1, 10, 20, 30 }, 15, 2, "Large gap, insert in middle");

        // Test Case 9: Boundary check (Minimum value)
        runTest(new int[] { -10, -5, 0, 5, 10 }, -11, 0, "Insert before minimum value");

        // Test Case 10: Boundary check (Maximum value)
        runTest(new int[] { -10, -5, 0, 5, 10 }, 11, 5, "Insert after maximum value");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(int[] nums, int target, int expected, String description) {
        System.out.println("Test Case: " + description);
        int result = searchInsert(nums, target);
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (result == expected ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }
}