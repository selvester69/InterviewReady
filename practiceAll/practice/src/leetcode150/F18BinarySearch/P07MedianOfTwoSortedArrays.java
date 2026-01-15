package leetcode150.F18BinarySearch;

public class P07MedianOfTwoSortedArrays {
    // brute force
    public double findMedianSortedArrays_brute(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }
        while (i < m) {
            merged[k++] = nums1[i++];
        }
        while (j < n) {
            merged[k++] = nums2[j++];
        }

        if (merged.length % 2 == 0) {
            return (merged[merged.length / 2] + merged[(merged.length - 1) / 2]) / 2.0;
        } else {
            return merged[merged.length / 2] / 1.0;
        }
    }

    // using similar to above wihout extra space
    public double findMedianSortedArrays_space(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length, i = 0, j = 0;
        int m1 = -1, m2 = -1;// to store middle element.
        for (int count = 0; count <= (m + n) / 2; count++) {// loop till middle
            m2 = m1;
            if (i != n && j != m) {
                m1 = (nums1[i] > nums2[j]) ? nums2[j++] : nums1[i++];
            } else if (i < n) {
                m1 = nums1[i++];
            } else {
                m1 = nums2[j++];
            }
        }
        if ((m + n) % 2 == 1) {
            return m1;
        } else {
            return (m1 + m2) / 2.0;
        }
    }

    // using binary search approach.
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        int half = (m + n + 1) / 2;
        int start = 0, end = m;
        while (start <= end) {
            int mid1 = start + (end - start) / 2;
            int mid2 = half - mid1;
            int maxLeftA = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int minRightA = mid1 == m ? Integer.MAX_VALUE : nums1[mid1];
            int maxLeftB = mid2 == 0 ? Integer.MAX_VALUE : nums2[mid2 - 1];
            int minRightB = mid2 == n ? Integer.MAX_VALUE : nums2[mid2];

            // apply bianry search
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }

            } else if (maxLeftA > minRightB) {
                end = mid1 - 1;
            } else {
                start = mid1 + 1;
            }
        }
        return 0.0;
    }

}