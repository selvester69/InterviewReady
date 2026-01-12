package leetcode150.F03Slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array arr[] and an integer k, return the count of distinct numbers
 * in all windows of size k.
 * 
 * Examples:
 * 
 * Input: arr[] = [1, 2, 1, 3, 4, 2, 3], k = 4
 * Output: [3, 4, 4, 3]
 * Explanation: First window is [1, 2, 1, 3], count of distinct numbers is 3.
 * Second window is [2, 1, 3, 4] count of distinct numbers is 4.
 * Third window is [1, 3, 4, 2] count of distinct numbers is 4.
 * Fourth window is [3, 4, 2, 3] count of distinct numbers is 3.
 * 
 * Input: arr[] = [4, 1, 1], k = 2
 * Output: [2, 1]
 * Explanation: First window is [4, 1], count of distinct numbers is 2.
 * Second window is [1, 1], count of distinct numbers is 1.
 */
public class P01DistinctNumbersInWindowOfFixedSize {

    public static int[] countDistinctElementBrute_force(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length - k+1; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }
            res[index++] = set.size();
            set.clear();
        }
        return res;
    }

    public static int[] countDistinctElement(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int i = 0, j = 0, index = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (j < nums.length) {
            // do calculations
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                // window size achieved
                res[index++] = map.size();
                // slide the window;
                if (map.get(nums[i]) > 0) {
                    map.put(nums[i], map.get(nums[i]) - 1);
                    if (map.get(nums[i]) == 0) {
                        map.remove(nums[i]);
                    }
                }
                i++;
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countDistinctElementBrute_force(new int[] { 1, 2, 1, 3, 4, 2, 3 }, 4)));
        System.out.println(Arrays.toString(countDistinctElement(new int[] { 1, 2, 1, 3, 4, 2, 3 }, 4)));
    }
}