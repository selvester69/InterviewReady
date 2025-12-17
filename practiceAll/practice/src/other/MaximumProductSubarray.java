package other;

public class MaximumProductSubarray {

    /** brute force way in O(N^2) time */

    public static int maxProductBruteForce(int[] arr) {
        int maxProd = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int prod = 1;
            for (int j = i; j < arr.length; j++) {
                prod *= arr[j];
                maxProd = Math.max(maxProd, prod);
            }
        }
        return maxProd;
    }

    /** using greedy approach O(N) time */

    public static int maxProduct(int[] arr) {
        int maxProd = arr[0];
        int currMin = arr[0], currMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int temp = Math.max(arr[i], Math.max(arr[i] * currMax, arr[i] * currMin));
            currMin = Math.min(arr[i], Math.min(arr[i] * currMax, arr[i] * currMin));
            currMax = temp;
            maxProd = Math.max(currMax, maxProd);
        }
        return maxProd;
    }

    /** using prefix and suffix array */
    public static int maxProduct_1(int[] arr) {
        int maxProd = 1;
        int pre = 1, suff = 1, n = arr.length;
        for (int i = 0; i < n; i++) {
            // if prefix or suffix becomes zero we have to make it 1 othewise we can get wrong ans 
            if (pre == 0)
                pre = 1;
            if (suff == 0)
                suff = 1;
            pre *= arr[i]; 
            suff *= arr[n - i - 1];
            maxProd = Math.max(maxProd, Math.max(pre, suff));
        }
        return maxProd;
    }

    public static void main(String[] args) {
        System.out.println(maxProductBruteForce(new int[] { -2, 6, -3, -10, 0, 2 }));
        System.out.println(maxProductBruteForce(new int[] { -1, -3, -10, 0, 6 }));
        System.out.println(maxProductBruteForce(new int[] { 2, 3, 4 }));

        System.out.println(maxProduct(new int[] { -2, 6, -3, -10, 0, 2 }));
        System.out.println(maxProduct(new int[] { -1, -3, -10, 0, 6 }));
        System.out.println(maxProduct(new int[] { 2, 3, 4 }));

        System.out.println(maxProduct_1(new int[] { -2, 6, -3, -10, 0, 2 }));
        System.out.println(maxProduct_1(new int[] { -1, -3, -10, 0, 6 }));
        System.out.println(maxProduct_1(new int[] { 2, 3, 4 }));
    }

}
