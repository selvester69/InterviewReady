package other;

public class MaxSubarraySum {

    /** using bruteforrce method-> calculate all subarray sum and find max sum among them 
     * time : O(N^2)
     * space: O(1)
    */
    public static int maxSubarrSumBruteforce(int[]arr){
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            int sum = 0;
            for(int j=i;j<arr.length;j++){
                sum+=arr[j];
                maxSum = Math.max(sum,maxSum);
            }
        }
        return maxSum;
    }

    /**
     * Using Kadane's Algorithm - using one pass
     * time: O(N)
     * space: O(1)
     */

    public static int maxSubarraySum(int[]arr){
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i=0;i<arr.length;i++){
            currSum+=arr[i];
            maxSum = Math.max(maxSum, currSum);
            if(currSum<0){
                currSum=0;
            }
        }
        return maxSum;
    }

    /** using one more line optimizations */
    public static int maxSubarraySum_1(int[]arr){
        int maxSum = arr[0]; // Note keep intial values as first value in array.
        int currSum = arr[0];
        for(int i=1;i<arr.length;i++){
            currSum = Math.max(currSum+arr[i],arr[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


    public static void main(String[] args) {
        System.out.println(maxSubarrSumBruteforce(new int[] { 2, 3, -8, 7, -1, 2, 3 }));
        System.out.println(maxSubarrSumBruteforce(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubarrSumBruteforce(new int[] { -2, -4 }));
        // ---
        System.out.println(maxSubarraySum(new int[] { 2, 3, -8, 7, -1, 2, 3 }));
        System.out.println(maxSubarraySum(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubarraySum(new int[] { -2, -4 }));
        // ---
        System.out.println(maxSubarraySum_1(new int[] { 2, 3, -8, 7, -1, 2, 3 }));
        System.out.println(maxSubarraySum_1(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(maxSubarraySum_1(new int[] { -2, -4 }));
    }

}
