package leetcode150.F04Matrix;

public class P05GameOfLifeWithoutBitManipulation {

    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] copy = new int[rows][cols]; // copy of previous board.

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = board[i][j];
            }
        }
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = 0;

                for (int[] d : directions) {
                    int r = i + d[0];
                    int c = j + d[1];

                    if (r >= 0 && r < rows && c >= 0 && c < cols && copy[r][c] == 1) {
                        liveNeighbors++;
                    }
                }

                if (copy[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
}
