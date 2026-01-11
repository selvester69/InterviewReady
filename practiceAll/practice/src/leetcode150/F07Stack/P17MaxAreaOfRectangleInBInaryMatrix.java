package leetcode150.F07Stack;

// 85. Maximal Rectangle
public class P17MaxAreaOfRectangleInBInaryMatrix {

    public static int maxRectangle(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0)
            return 0;
        int maxArea = 0;
        int[] prefix = new int[nums[0].length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (nums[i][j] == 0) {
                    prefix[j] = 0;
                } else {
                    prefix[j] = prefix[j] + nums[i][j];

                }
            }
            maxArea = Math.max(maxArea, P16MaxAreaOfHistogram.largestHist(prefix));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0 } };
        System.out.println(maxRectangle(matrix));
    }
}
