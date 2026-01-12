package leetcode150.F04Matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P05GameOfLifeInfiniteBoard {

    public void gameOfLife(int[][] board) {
        Set<String> liveCells = new HashSet<>();
        int rows = board.length;
        int cols = board[0].length;
        // calculate live cells in set
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 1) {
                    liveCells.add(i + "," + j);
                }
            }
        }

        Map<String, Integer> neighbourCount = new HashMap<>();
        // calcualte neighbours in Map for those live cells
        for (String cell : liveCells) {
            String parts[] = cell.split(",");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    String neighbour = (r + i) + "," + (c + j);
                    neighbourCount.put(neighbour, neighbourCount.getOrDefault(neighbour, 0) + 1);
                }
            }
        }
        // update board based on condition
        for (Map.Entry<String, Integer> entry : neighbourCount.entrySet()) {
            String key = entry.getKey();
            int count = entry.getValue();
            String parts[] = key.split(",");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);

            if (r >= 0 && r < rows && c >= 0 && c < cols) {
                if (count == 3) {
                    board[r][c] = 1;
                } else if (count == 2 && liveCells.contains(key)) {
                    board[r][c] = 1;
                } else if (!liveCells.contains(key) || (count != 2 || count != 3)) {
                    board[r][c] = 0;

                }
            }
        }

        for (String cell : liveCells) {
            if (!neighbourCount.containsKey(cell)) {
                String[] parts = cell.split(",");
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                board[r][c] = 0;
            }
        }
    }
}
