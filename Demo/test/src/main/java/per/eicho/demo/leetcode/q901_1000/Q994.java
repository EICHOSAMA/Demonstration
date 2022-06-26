package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>994. Rotting Oranges 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rotting-oranges/">
 *   994. Rotting Oranges</a>
 */
public final class Q994 {

    private int[][] directions = new int[][]{
        new int[]{-1,  0},
        new int[]{ 1,  0},
        new int[]{ 0, -1},
        new int[]{ 0,  1}
    };

    public int orangesRotting(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 10
        // 4. grid[i][j] is 0, 1, or 2.        
        final int m = grid.length;
        final int n = grid[0].length;
        final boolean[] mark = new boolean[m * n];
        final Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int val = grid[i][j];
                if (val == 2) {
                    final int idx = i * n + j;
                    mark[i * n + j] = true;
                    queue.add(idx);
                } else if (val == 1) {
                    count++;
                }
            }
        }

        int minute = 0;
        while (!queue.isEmpty()) {
            if (count == 0) break;
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                final int idx = queue.poll();
                final int x = idx / n;
                final int y = idx % n;

                for (int[] direction : directions) {
                    final int nx = x + direction[0];
                    if (nx < 0 || nx >= m) continue;
                    final int ny = y + direction[1];
                    if (ny < 0 || ny >= n) continue;
                    
                    if (grid[nx][ny] != 1) continue;
                    final int nIdx = nx * n + ny;
                    if (mark[nIdx]) continue;
                    grid[nx][ny] = 2;
                    mark[nIdx] = true;
                    count--;
                    queue.add(nIdx);
                }
            }
            minute++;
        }

        return count == 0 ? minute : -1;
    }
}
