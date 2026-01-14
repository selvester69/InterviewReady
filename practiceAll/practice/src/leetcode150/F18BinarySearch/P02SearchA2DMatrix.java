package leetcode150.F18BinarySearch;

import java.util.Arrays;

public class P02SearchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        // applying the logic of row+col wise sorted matrix
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = n - 1;
        while (start >= 0 && start < m && end >= 0 && end < n) {
            if (matrix[start][end] == target) {
                return true;
            } else if (target < matrix[start][end]) {
                end--;
            } else {
                start++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Search a 2D Matrix ===\n");

        // Test Case 1: Target exists in the matrix (LeetCode Example 1)
        int[][] matrix1 = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        runTest(matrix1, 3, true, "Target exists in the matrix");

        // Test Case 2: Target does not exist (LeetCode Example 2)
        runTest(matrix1, 13, false, "Target does not exist");

        // Test Case 3: Single element matrix, target matches
        int[][] matrix3 = { { 1 } };
        runTest(matrix3, 1, true, "Single element, target matches");

        // Test Case 4: Single element matrix, target missing
        runTest(matrix3, 0, false, "Single element, target missing");

        // Test Case 5: Target is the very first element
        runTest(matrix1, 1, true, "Target is the first element (boundary)");

        // Test Case 6: Target is the very last element
        runTest(matrix1, 60, true, "Target is the last element (boundary)");

        // Test Case 7: Target is smaller than any element
        runTest(matrix1, 0, false, "Target smaller than minimum");

        // Test Case 8: Target is larger than any element
        runTest(matrix1, 100, false, "Target larger than maximum");

        // Test Case 9: Matrix with only one row
        int[][] matrix9 = { { 1, 3, 5, 7 } };
        runTest(matrix9, 5, true, "Matrix with a single row");

        // Test Case 10: Matrix with only one column
        int[][] matrix10 = { { 1 }, { 3 }, { 5 }, { 7 } };
        runTest(matrix10, 3, true, "Matrix with a single column");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(int[][] matrix, int target, boolean expected, String description) {
        System.out.println("Test Case: " + description);
        boolean result = searchMatrix(matrix, target);

        // Formatting matrix for display
        String matrixStr = matrix.length <= 4 ? Arrays.deepToString(matrix) : "[...]";

        System.out.println("Input: matrix = " + matrixStr + ", target = " + target);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (result == expected ? "✓ PASS" : "✗ FAIL"));
        System.out.println();
    }
}