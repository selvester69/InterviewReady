package leetcode150.F04Matrix;

import java.util.Arrays;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as
 * Life, is a cellular automaton devised by the British mathematician John
 * Horton Conway in 1970."
 * 
 * The board is made up of an m x n grid of cells, where each cell has an
 * initial state: live (represented by a 1) or dead (represented by a 0). Each
 * cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 * 
 * Any live cell with fewer than two live neighbors dies as if caused by
 * under-population.
 * Any live cell with two or three live neighbors lives on to the next
 * generation.
 * Any live cell with more than three live neighbors dies, as if by
 * over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by
 * reproduction.
 * The next state of the board is determined by applying the above rules
 * simultaneously to every cell in the current state of the m x n grid board. In
 * this process, births and deaths occur simultaneously.
 * 
 * Given the current state of the board, update the board to reflect its next
 * state.
 * 
 * Note that you do not need to return anything.
 * 
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * 
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 * 
 * 
 */
public class P05GameOfLife {
    static int[][] dirn = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

    public static void gameOfLife(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int nei = liveNeighbour(board, i, j);
                if (board[i][j] == 1 && (nei == 2 || nei == 3)) {
                    board[i][j] |= 2;
                }
                // dead cell
                if (board[i][j] == 0 && nei == 3) {
                    board[i][j] |= 2;
                }

            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    public static int liveNeighbour(int[][] board, int i, int j) {
        int nei = 0;
        for (int[] d : dirn) {
            int x = i + d[0];
            int y = j + d[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y]&1) == 1) {
                nei++;
            }
        }
        return nei;
    }

    public static void main(String[] args) {
        int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
        printMatrix(board);
        gameOfLife(board);
        printMatrix(board);

    }

    static void printMatrix(int[][] matrix) {
        for (int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println("==================");
    }
}
