package per.eicho.demo.leetcode.q1101_1200;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>1162. As Far from Land as Possible 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/as-far-from-land-as-possible/">
 *   1162. As Far from Land as Possible</a>
 */
public final class Q1162 {

    private static final int[][] directions = new int[][]{
        new int[]{0, -1}, new int[]{-1, 0},
        new int[]{0,  1}, new int[]{ 1, 0},
    };

    private static final int WATER = 0;

    public int maxDistance(int[][] grid) {
        // 1. n == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= n <= 100
        // 4. grid[i][j] is 0 or 1
        final int n = grid.length;
        int[][] distance = new int[n][n];
        final Queue<int[]> bfsQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == WATER) continue;
                bfsQueue.add(new int[]{i, j}); 
            }
        }

        if (bfsQueue.isEmpty() || bfsQueue.size() == n * n) return -1;

        while (!bfsQueue.isEmpty()) {
            final int[] point = bfsQueue.poll();
            final int dis = distance[point[0]][point[1]];

            for (int[] direction : directions) {
                final int nx = point[0] + direction[0];
                if (nx < 0 || nx >= n) continue;
                final int ny = point[1] + direction[1];
                if (ny < 0 || ny >= n) continue;
                if (grid[nx][ny] != WATER) continue;
                if (distance[nx][ny] != 0) continue;

                bfsQueue.add(new int[]{nx, ny});
                distance[nx][ny] = dis + 1;
            }
        }

        int result = 0;
        for (int[] row : distance) {
            for (int dis : row) {
                result = Math.max(result, dis);
            }
        }

        return result;
    }
}
