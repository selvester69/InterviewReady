package leetcode150.F01ArrayString;

public class P01MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        m--;n--;
        while (k > 0 && n>0) {
            if (nums1[m] > nums2[n]) {
                nums1[k] = nums1[m--];
            } else {
                nums1[k] = nums2[n--];
            }
            k--;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 0, 0, 0 };
        int[] arr2 = { 2, 5, 6 };
        merge(arr, 3, arr2, 3);

    }
}
