package other;

import java.util.ArrayList;
import java.util.List;

public class PrintSubarrayWithMaximumSum {

    /**
     * using bruteforrce method-> calculate all subarray sum and find max sum among
     * them
     * time : O(N^2)
     * space: O(1)
     */

    public static List<Integer> maxSubarrSumBruteforce(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int maxSum = Integer.MIN_VALUE;
        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            int currSum = 0;
            for (int j = i; j < arr.length; j++) {
                currSum += arr[j];
                if (currSum > maxSum) {
                    maxSum = currSum;
                    start = i;
                    end = j;
                }
            }
        }
        for (int i = start; i <= end; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    /**
     * sliding window will not be applicable as we dont know window condition
     * kadane's algorithm will work.
     * time: O(N)
     * space: O(1)
     */
    public static List<Integer> maxSubarraySum(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        int tempStart = 0;
        int currSum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (currSum > maxSum) {
                maxSum = currSum;
                start = tempStart;
                end = i;
            }
            if (currSum < 0) {
                currSum = 0;
                tempStart = i + 1;
            }
        }
        for (int i = start; i <= end; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxSubarrSumBruteforce(new int[] { 2, 3, -8, 7, -1, 2, 3 }));
        System.out.println(maxSubarrSumBruteforce(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubarrSumBruteforce(new int[] { -2, -4 }));
        // ---
        System.out.println(maxSubarraySum(new int[] { 2, 3, -8, 7, -1, 2, 3 }));
        System.out.println(maxSubarraySum(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubarraySum(new int[] { -2, -4 }));

    }

}
