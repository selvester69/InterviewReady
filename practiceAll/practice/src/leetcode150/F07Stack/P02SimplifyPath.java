package leetcode150.F07Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given an absolute path for a Unix-style file system, which always
 * begins with a slash '/'. Your task is to transform this absolute path into
 * its simplified canonical path.
 * 
 * The rules of a Unix-style file system are as follows:
 * 
 * A single period '.' represents the current directory.
 * A double period '..' represents the previous/parent directory.
 * Multiple consecutive slashes such as '//' and '///' are treated as a single
 * slash '/'.
 * Any sequence of periods that does not match the rules above should be treated
 * as a valid directory or file name. For example, '...' and '....' are valid
 * directory or file names.
 * The simplified canonical path should follow these rules:
 * 
 * The path must start with a single slash '/'.
 * Directories within the path must be separated by exactly one slash '/'.
 * The path must not end with a slash '/', unless it is the root directory.
 * The path must not have any single or double periods ('.' and '..') used to
 * denote current or parent directories.
 * Return the simplified canonical path.
 * 
 * Example 1:
 * 
 * Input: path = "/home/"
 * 
 * Output: "/home"
 * 
 * Explanation:
 * 
 * The trailing slash should be removed.
 * 
 * Example 2:
 * 
 * Input: path = "/home//foo/"
 * 
 * Output: "/home/foo"
 * 
 * Explanation:
 * 
 * Multiple consecutive slashes are replaced by a single one.
 * 
 * Example 3:
 * 
 * Input: path = "/home/user/Documents/../Pictures"
 * 
 * Output: "/home/user/Pictures"
 * 
 * Explanation:
 * 
 * A double period ".." refers to the directory up a level (the parent
 * directory).
 * 
 * Example 4:
 * 
 * Input: path = "/../"
 * 
 * Output: "/"
 * 
 * Explanation:
 * 
 * Going one level up from the root directory is not possible.
 * 
 * Example 5:
 * 
 * Input: path = "/.../a/../b/c/../d/./"
 * 
 * Output: "/.../b/d"
 * 
 * Explanation:
 * 
 * "..." is a valid name for a directory in this problem.
 * 
 * 
 * 
 * Constraints:
 * 
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class P02SimplifyPath {

    /**
     * LeetCode 71: Simplify Path
     * Constraints:
     * 1 <= path.length <= 3000
     * path consists of English letters, digits, period '.', slash '/' or '_'.
     * path is a valid absolute Unix path.
     */
    public static String simplifyPath(String path) {
        if (path.length() == 0)
            return "";
        String[] paths = path.split("/");
        System.out.println(Arrays.toString(paths));
        Stack<String> st = new Stack<>();
        for (String p : paths) {
            if (p.equals("") || p.equals(".")) {
                continue;
            } else if (p.equals("..")) {
                if (!st.isEmpty())
                    st.pop();
            } else {
                st.push(p);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.insert(0, "/" + st.pop());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== LeetCode Test Cases: Simplify Path ===\n");

        runTest("/../", "/", "Simple path with trailing slash");

        // Test Case 1: Simple path
        runTest("/home/", "/home", "Simple path with trailing slash");

        // Test Case 2: Multiple slashes
        runTest("/home//foo/", "/home/foo", "Redundant multiple slashes");

        // Test Case 3: Current directory (.)
        runTest("/home/user/./downloads/", "/home/user/downloads", "Path with current directory symbol (.)");

        // Test Case 4: Parent directory (..)
        runTest("/../", "/", "Root parent directory (should remain root)");

        // Test Case 5: Complex navigation
        runTest("/home/../../..", "/", "Navigating multiple levels above root");

        // Test Case 6: Mixed navigation
        runTest("/a/./b/../../c/", "/c", "Mixed navigation with dots and parent directories");

        // Test Case 7: Path as names
        runTest("/.../a/../b/c/../d/./", "/.../b/d", "Handling '...' as a valid directory name");

        // Test Case 8: Minimum constraint
        runTest("/", "/", "Single root slash");

        // Test Case 9: Consecutive dots in name
        runTest("/hello../world", "/hello../world", "Dots treated as part of the filename");

        // Test Case 10: Typical Unix-style structure
        runTest("/home/user/Documents/../Pictures", "/home/user/Pictures", "Typical folder navigation");

        System.out.println("=== All test cases completed ===");
    }

    /**
     * Helper method to run tests and format output.
     */
    private static void runTest(String path, String expected, String description) {
        System.out.println("Description: " + description);
        String result = simplifyPath(path);
        System.out.println("Input:    \"" + path + "\"");
        System.out.println("Expected: \"" + expected + "\"");
        System.out.println("Actual:   \"" + result + "\"");
        System.out.println("Result:   " + (result.equals(expected) ? "✓ PASS" : "✗ FAIL"));
        System.out.println("-----------------------------------");
    }
}
