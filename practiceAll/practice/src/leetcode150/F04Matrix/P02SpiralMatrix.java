package leetcode150.F04Matrix;

import java.util.ArrayList;
import java.util.List;

public class P02SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int startRow = 0, startCol = 0, endRow = matrix.length - 1, endCol = matrix[0].length - 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int col = startCol; col <= endCol; col++) {
                res.add(matrix[startRow][col]);
            }
            startRow++;
            for (int row = startRow; row <= endRow; row++) {
                res.add(matrix[row][endCol]);
            }
            endCol--;
            if (startRow <= endRow) {
                for (int col = endCol; col >= startCol; col--) {
                    res.add(matrix[endRow][col]);
                }
                endRow--;
            }
            if (startCol <= endCol) {
                for (int row = endRow; row >= startRow; row--) {
                    res.add(matrix[row][startCol]);
                }
                startCol++;
            }
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {

    }

}
