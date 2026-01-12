package leetcode150.F04Matrix;

import java.util.Arrays;

public class P03RotateImage {

    public static void rotate(int[][] matrix) {
        // transpose and change cols
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        printMatrix(matrix);
        int colS = 0, colE = matrix[0].length - 1;
        while (colS < colE) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = matrix[j][colS];
                matrix[j][colS] = matrix[j][colE];
                matrix[j][colE] = temp;
            }
            colS++;
            colE--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        printMatrix(matrix);
        rotate(matrix);

        printMatrix(matrix);
    }

    static void printMatrix(int[][] matrix) {
        for (int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println("==================");
    }

}
