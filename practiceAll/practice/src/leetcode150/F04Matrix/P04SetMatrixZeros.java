package leetcode150.F04Matrix;

public class P04SetMatrixZeros {

    // brute force way is to create new res matrix
    // and update row and col as zero once we encounter zero in original matrix

    public static void setZeroes(int[][] matrix) {
        boolean isRowZero = false, isColZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                isRowZero = true;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0)
                isColZero = true;
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // mark all rows/ cols as zero.
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (isRowZero)
                matrix[i][0] = 0;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (isColZero)
                matrix[0][i] = 0;
        }
    }

    public static void main(String[] args) {

    }
}
