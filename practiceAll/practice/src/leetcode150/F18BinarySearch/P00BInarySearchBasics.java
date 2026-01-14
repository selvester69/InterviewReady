package leetcode150.F18BinarySearch;

public class P00BInarySearchBasics {

    // for bianry search condition is that nums should be sorted.
    // return searched index.
    public int search(int[] nums, int target) {
        // add edge cases
        if (nums == null || nums.length == 0)
            return -1;

        int start = 0, end = nums.length - 1;
        while (start <= end) {
            // int mid = (start + end) / 2;// this can cause integer overflow if arr size is
            // greater than INteger.MAX_VALUE
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (target < nums[mid]) {// target will be prsent in smaller side
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

}
