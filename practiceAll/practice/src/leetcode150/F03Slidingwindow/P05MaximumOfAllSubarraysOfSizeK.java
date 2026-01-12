package leetcode150.F03Slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * You are given an array of integers nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right. You can
 * only see the k numbers in the window. Each time the sliding window moves
 * right by one position.
 * 
 * Return the max sliding window.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position Max
 * --------------- -----
 * [1 3 -1] -3 5 3 6 7 3
 * 1 [3 -1 -3] 5 3 6 7 3
 * 1 3 [-1 -3 5] 3 6 7 5
 * 1 3 -1 [-3 5 3] 6 7 5
 * 1 3 -1 -3 [5 3 6] 7 6
 * 1 3 -1 -3 5 [3 6 7] 7
 * Example 2:
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class P05MaximumOfAllSubarraysOfSizeK {

    public static int[] maxSlidingWindow_brute(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[index++] = max;
        }
        return res;
    }

    public static int[] maxSlidingWindow_heap_brute_force(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());// max heap
        int i = 0, j = 0;
        while (j < nums.length) {
            pq.add(nums[j]);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {// window matched
                // ans from calc
                res[index++] = pq.peek();
                // slide the window
                pq.remove(nums[i]);
                i++;
                j++;
            }
        }
        return res;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        Deque<Integer> q = new ArrayDeque<>();
        int i = 0, j = 0;
        while (j < nums.length) {
            while (!q.isEmpty() && q.peekLast() < nums[j]) {
                q.pollLast();
            }
            q.addLast(nums[j]);
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {// window matched
                // ans from calc
                res[index++] = q.peek();
                // slide the window
                if (!q.isEmpty() && q.peekFirst() == nums[i]) {
                    q.removeFirst();
                }
                i++;
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow_brute(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
        System.out.println(Arrays.toString(
                maxSlidingWindow_heap_brute_force(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
    }
}