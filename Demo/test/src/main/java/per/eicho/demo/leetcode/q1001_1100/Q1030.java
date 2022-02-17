package per.eicho.demo.leetcode.q1001_1100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>1030. Matrix Cells in Distance Order 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/matrix-cells-in-distance-order/">
 *   1030. Matrix Cells in Distance Order</a>
 */
public final class Q1030 {
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        // 1. 1 <= rows, cols <= 100
        // 2. 0 <= rCenter < rows
        // 3. 0 <= cCenter < cols        
        final int[][] result = new int[rows * cols][];
        final boolean[][] used = new boolean[rows][cols];
        Queue<int[]> currentLayer = new LinkedList<>(); 
        Queue<int[]> nextLayer = new LinkedList<>();
        currentLayer.add(new int[]{rCenter, cCenter});
        used[rCenter][cCenter] = true;

        int p = 0;
        while (!currentLayer.isEmpty()) {
            
            while (!currentLayer.isEmpty()) {
                final int[] coordinate = currentLayer.poll();
                result[p++] = coordinate;
                
                final int row = coordinate[0];
                final int col = coordinate[1];
                check(row - 1, col, used, nextLayer);
                check(row + 1, col, used, nextLayer);
                check(row, col - 1, used, nextLayer);
                check(row, col + 1, used, nextLayer);
            }

            final Queue<int[]> temp = currentLayer;
            currentLayer = nextLayer;
            nextLayer = temp;
        }


        return result;
    }

    private void check(int row, int col, boolean[][] used, Queue<int[]> nextLayer) {
        if (row < 0 || row >= used.length) return;
        if (col < 0 || col >= used[0].length) return;
        if (used[row][col]) return;
        used[row][col] = true;
        nextLayer.add(new int[]{row, col});
    }
}
