package leetcode150.F04Matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P01ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new Set[9];
        Set<Character>[] cols = new Set[9];
        Set<Character>[] boxes = new Set[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (rows[i].contains(board[i][j]) ||
                        cols[j].contains(board[i][j]) ||
                        boxes[i / 3 + j / 3].contains(board[i][j])) {
                    return false;
                } else {
                    rows[i].add(board[i][j]);
                    cols[j].add(board[i][j]);
                    boxes[i / 3 + j / 3].add(board[i][j]);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P01ValidSudoku solution = new P01ValidSudoku();
        System.out.println("=== LeetCode Test Cases ===\n");

        // --- Test Cases ---

        // Test Case 1: Example 1 (Valid Sudoku)
        System.out.println("Test Case 1: LeetCode Example 1 - Valid partial board.");
        char[][] board1 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        boolean expected1 = true;
        boolean result1 = solution.isValidSudoku(board1);
        System.out.println("Input: " + formatBoard(board1));
        System.out.println("Expected: " + expected1);
        System.out.println("Actual: " + result1);
        System.out.println("Result: " + (result1 == expected1 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 2: Example 2 (Invalid Sudoku - Duplicate in 3x3 box)
        System.out.println("Test Case 2: LeetCode Example 2 - Invalid (two '8's in top-left 3x3 box).");
        char[][] board2 = {
                { '8', '3', '.', '.', '7', '.', '.', '.', '.' }, // '8' is the issue
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        boolean expected2 = false;
        boolean result2 = solution.isValidSudoku(board2);
        System.out.println("Input: " + formatBoard(board2));
        System.out.println("Expected: " + expected2);
        System.out.println("Actual: " + result2);
        System.out.println("Result: " + (result2 == expected2 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 3: Invalid - Duplicate in a Row
        System.out.println("Test Case 3: Invalid - Duplicate '7' in the first row.");
        char[][] board3 = {
                { '7', '3', '.', '.', '7', '.', '.', '.', '.' }, // Two '7's in row 0
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        boolean expected3 = false;
        boolean result3 = solution.isValidSudoku(board3);
        System.out.println("Input: " + formatBoard(board3));
        System.out.println("Expected: " + expected3);
        System.out.println("Actual: " + result3);
        System.out.println("Result: " + (result3 == expected3 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 4: Invalid - Duplicate in a Column
        System.out.println("Test Case 4: Invalid - Duplicate '6' in the first column.");
        char[][] board4 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '6', '.', '.', '.', '6', '.', '.', '.', '3' }, // Second '6' in col 0
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        boolean expected4 = false;
        boolean result4 = solution.isValidSudoku(board4);
        System.out.println("Input: " + formatBoard(board4));
        System.out.println("Expected: " + expected4);
        System.out.println("Actual: " + result4);
        System.out.println("Result: " + (result4 == expected4 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 5: Boundary Case - Fully Valid Board
        System.out.println("Test Case 5: Boundary - Fully filled, valid board (a solved Sudoku).");
        char[][] board5 = {
                { '5', '1', '9', '7', '4', '8', '3', '6', '2' },
                { '6', '2', '8', '1', '5', '3', '7', '9', '4' },
                { '4', '3', '7', '6', '2', '9', '1', '8', '5' },
                { '3', '7', '2', '4', '6', '1', '8', '5', '9' },
                { '9', '8', '5', '3', '7', '2', '6', '4', '1' },
                { '1', '6', '4', '8', '9', '5', '2', '7', '3' },
                { '2', '5', '3', '9', '1', '4', 'X', '2', 'Y' }, // Board is not 9x9 and has invalid chars, must use
                                                                 // valid 9x9 grid
                { '2', '5', '3', '9', '1', '4', 'X', '2', 'Y' }, // Re-using a known valid 9x9
                { '2', '5', '3', '9', '1', '4', '8', '7', '6' }
        };
        // Re-using a known valid 9x9 for this test case
        char[][] board5_valid = {
                { '5', '1', '9', '7', '4', '8', '3', '6', '2' },
                { '6', '2', '8', '1', '5', '3', '7', '9', '4' },
                { '4', '3', '7', '6', '2', '9', '1', '8', '5' },
                { '3', '7', '2', '4', '6', '1', '8', '5', '9' },
                { '9', '8', '5', '3', '7', '2', '6', '4', '1' },
                { '1', '6', '4', '8', '9', '5', '2', '7', '3' },
                { '2', '5', '3', '9', '1', '4', '6', '8', '7' },
                { '7', '9', '1', '2', '8', '6', '4', '3', '5' },
                { '8', '4', '6', '5', '3', '7', '9', '1', '2' }
        };
        boolean expected5 = true;
        boolean result5 = solution.isValidSudoku(board5);
        System.out.println("Input: " + formatBoard(board5_valid));
        System.out.println("Expected: " + expected5);
        System.out.println("Actual: " + result5);
        System.out.println("Result: " + (result5 == expected5 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 6: Edge Case - Mostly Empty Board (Valid)
        System.out.println("Test Case 6: Edge Case - Mostly empty board (only one filled cell).");
        char[][] board6 = new char[9][9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(board6[i], '.');
        }
        board6[0][0] = '9';
        boolean expected6 = true;
        boolean result6 = solution.isValidSudoku(board6);
        System.out.println("Input: " + formatBoard(board6));
        System.out.println("Expected: " + expected6);
        System.out.println("Actual: " + result6);
        System.out.println("Result: " + (result6 == expected6 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 7: Edge Case - Invalid character (Not '1'-'9' or '.')
        System.out.println(
                "Test Case 7: Edge Case - Invalid character '0'. Should still be considered 'filled' and check repetition.");
        char[][] board7 = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '0', '.', '.', '7', '9' } // '0' is technically an invalid Sudoku number, but the
                                                                // problem only says '1'-'9' without repetition.
                                                                // Assuming inputs only contain '1'-'9' or '.', this
                                                                // test is for repetition/sub-box.
                                                                // Using a duplicate in a sub-box as a simpler test.
        };
        System.out.println("Test Case 7: Edge Case - Duplicate '9' across 3x3 boxes (Valid).");
        char[][] board7_valid = {
                { '9', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        }; // '9's are in separate rows/cols/boxes - VALID
        boolean expected7 = true;
        boolean result7 = solution.isValidSudoku(board7);
        System.out.println("Input: " + formatBoard(board7_valid));
        System.out.println("Expected: " + expected7);
        System.out.println("Actual: " + result7);
        System.out.println("Result: " + (result7 == expected7 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        // Test Case 8: Boundary Case - Max repetition across entire board (Valid)
        System.out.println("Test Case 8: Complex Case - Every number used once, but in a valid structure (Valid).");
        char[][] board8 = {
                { '1', '2', '3', '.', '.', '.', '.', '.', '.' },
                { '4', '5', '6', '.', '.', '.', '.', '.', '.' },
                { '7', '8', '9', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '1', '2', '3', '.', '.', '.' },
                { '.', '.', '.', '4', '5', '6', '.', '.', '.' },
                { '.', '.', '.', '7', '8', '9', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '1', '2', '3' },
                { '.', '.', '.', '.', '.', '.', '4', '5', '6' },
                { '.', '.', '.', '.', '.', '.', '7', '8', '9' }
        };
        boolean expected8 = true;
        boolean result8 = solution.isValidSudoku(board8);
        System.out.println("Input: " + formatBoard(board8));
        System.out.println("Expected: " + expected8);
        System.out.println("Actual: " + result8);
        System.out.println("Result: " + (result8 == expected8 ? "✓ PASS" : "✗ FAIL"));
        System.out.println();

        System.out.println("=== All test cases completed ===");
    }

    // Helper method to format a char[][] for clean output
    public static String formatBoard(char[][] board) {
        if (board == null)
            return "null";
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (char[] row : board) {
            sb.append("  [");
            for (int i = 0; i < row.length; i++) {
                sb.append("\"").append(row[i]).append("\"");
                if (i < row.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("],\n");
        }
        sb.setLength(sb.length() - 2); // Remove trailing comma and newline
        sb.append("\n]");
        return sb.toString();
    }

}
