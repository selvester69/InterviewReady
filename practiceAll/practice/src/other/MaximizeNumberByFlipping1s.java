package other;

public class MaximizeNumberByFlipping1s {

    // find max ones if we flip the array from any index to any index
    public static int maxOnesBurteForce(int[] nums) {
        int oneCount = 0;
        for (int i : nums) {
            oneCount += i;
        }
        int ans = oneCount;
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[i] == 0) {
                    cnt++;
                } else {
                    cnt--;
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    public static int maxOnesCount(int[] nums) {
        int oneCount = 0;
        // convert all 0's and 1's to +1 and -1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                nums[i] = -1;
                oneCount++;
            } else {
                nums[i] = 1;
            }
        }
        int maxDiff = subArrSumUtil(nums);
        return oneCount + maxDiff;
    }

    private static int subArrSumUtil(int[] nums) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    // find which index switch will give us max one's
    public static int[] maxOnes(int[] nums) {
        int[] ans = new int[2];
        int l = 0, r = 0, maxSum = Integer.MIN_VALUE, currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                currSum += 1;
            } else {
                currSum -= 1;
            }
            if (currSum > maxSum) {
                r = i;
                maxSum = currSum;
                ans[0] = l + 1;
                ans[1] = r + 1;
            }
            if (currSum < 0) {
                currSum = 0;
                l = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int res[] = maxOnes(new int[] { 0, 1, 0 });
        System.out.println(res[0] + " " + res[1]);
        res = maxOnes(new int[] { 0, 0, 1, 1, 1 });
        System.out.println(res[0] + " " + res[1]);
    }

}
