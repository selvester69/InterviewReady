package leetcode150.F17KadaneAlgorithm;

public class P02MaxSumCircularSubarray {

    public static int bruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += nums[(i + j) % nums.length];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }


    // TODO: fix the implementation
    public static void usingPrefix(int[]nums){
        int maxSum = Integer.MIN_VALUE;
        int[]prefix = new int[nums.length];
        int[]suff = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            prefix[i] = prefix[i-1]+nums[i];
        }
    }

    public static void main(String[] args) {
        System.out.println(bruteForce(new int[] { 8, -8, 9, -9, 10, -11, 12 }));
    }

}
