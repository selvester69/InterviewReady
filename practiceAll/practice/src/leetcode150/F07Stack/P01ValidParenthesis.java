package leetcode150.F07Stack;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * 
 * Example 1:
 * 
 * Input: s = "()"
 * 
 * Output: true
 * 
 * Example 2:
 * 
 * Input: s = "()[]{}"
 * 
 * Output: true
 * 
 * Example 3:
 * 
 * Input: s = "(]"
 * 
 * Output: false
 * 
 * Example 4:
 * 
 * Input: s = "([])"
 * 
 * Output: true
 * 
 * Example 5:
 * 
 * Input: s = "([)]"
 * 
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */

public class P01ValidParenthesis {
    /**
     * LeetCode 20: Valid Parentheses
     * Constraints:
     * 1 <= s.length <= 10^4
     * s consists of parentheses only '()[]{}'.
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() &&
                        ((stack.peek() == '[' && ch == ']') ||
                                (stack.peek() == '{' && ch == '}') ||
                                (stack.peek() == '(' && ch == ')'))) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Valid Parentheses ===\n");
        runTest("(])", false, "Unbalanced (extra closing at end)");
        runTest("]", false, "Unbalanced (extra closing at end)");

        // Test Case 1: Simple valid pair
        runTest("()", true, "Simple valid pair");

        // Test Case 2: Multiple valid types
        runTest("()[]{}", true, "Multiple valid types");

        // Test Case 3: Simple mismatch
        runTest("(]", false, "Simple mismatch");

        // Test Case 4: Nested valid parentheses
        runTest("([{}])", true, "Nested valid parentheses");

        // Test Case 5: Wrong closing order
        runTest("([)]", false, "Wrong closing order (interleaved)");

        // Test Case 6: Single opening character (Minimum constraint)
        runTest("(", false, "Single opening character");

        // Test Case 7: Single closing character
        runTest("]", false, "Single closing character");

        // Test Case 8: Long valid sequence
        runTest("(((((((((())))))))))", true, "Deeply nested valid sequence");

        // Test Case 9: Unbalanced (Extra opening)
        runTest("(){}[", false, "Unbalanced (extra opening at end)");

        // Test Case 10: Unbalanced (Extra closing)
        runTest("()})", false, "Unbalanced (extra closing at end)");
        runTest("(])", false, "Unbalanced (extra closing at end)");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(String input, boolean expected, String description) {
        System.out.println("Description: " + description);
        boolean result = isValid(input);
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);
        System.out.println("Result:   " + (result == expected ? "✓ PASS" : "✗ FAIL"));
        System.out.println("-----------------------------------");
    }
}
