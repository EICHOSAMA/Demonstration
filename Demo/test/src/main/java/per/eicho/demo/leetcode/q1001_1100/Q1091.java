package per.eicho.demo.leetcode.q1001_1100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>1091. Shortest Path in Binary Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/deepest-leaves-sum/">
 *   1091. Shortest Path in Binary Matrix</a>
 */
public final class Q1091 {

    private int[][] movements = new int[][]{
        new int[]{-1,-1}, new int[]{-1,0}, new int[]{-1,1},
        new int[]{0,-1}, new int[]{0,1},
        new int[]{1,-1}, new int[]{1,0}, new int[]{1,1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        // 1. n == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= n <= 100
        // 4. grid[i][j] is 0 or 1
        final int n = grid.length;         
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) return -1;
        final Queue<Integer> wfsQueue = new LinkedList<>();
        wfsQueue.add(encode(0, 0, n));
        grid[0][0] = -1;
        final int[] coordinate = new int[2];
        final int[] coordinate2Move = new int[2];
        while (!wfsQueue.isEmpty()) {
            final int idx = wfsQueue.poll();
            decode(idx, n, coordinate);
            final int dis = grid[coordinate[0]][coordinate[1]];

            for (int[] movement : movements) {
                if (!canMove(coordinate, movement, n, grid, coordinate2Move)) continue;

                wfsQueue.add(encode(coordinate2Move[0], coordinate2Move[1], n));
                grid[coordinate2Move[0]][coordinate2Move[1]] = dis - 1;
            }
        }
        return grid[n - 1][n - 1] < 0 ? -grid[n - 1][n - 1] : -1 ;
    }

    private int encode(int i, int j, int n) {
        return i * n + j;
    }

    private void decode(int idx, int n, int[] coordinateRef) {
        coordinateRef[0] = idx / n;
        coordinateRef[1] = idx % n;
    }

    private boolean canMove(int[] coordinate, int[] movement, int n, int[][] grid, int[] coordinate2Move) {
        final int newX = coordinate[0] + movement[0];
        if (newX < 0 || newX >= n) return false;
        final int newY = coordinate[1] + movement[1];
        if (newY < 0 || newY >= n) return false;
        if (grid[newX][newY] != 0) return false;
        coordinate2Move[0] = newX;
        coordinate2Move[1] = newY;
        return true;
    }
}
