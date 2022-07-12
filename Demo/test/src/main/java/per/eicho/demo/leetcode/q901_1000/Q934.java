package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>934. Shortest Bridge 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-bridge/">
 *   934. Shortest Bridge</a>
 */
public final class Q934 {

    private static final int[][] directions = new int[][]{
        new int[]{ 1, 0}, new int[]{0,  1},
        new int[]{-1, 0}, new int[]{0, -1}
    };

    public int shortestBridge(int[][] grid) {
        // 1. n == grid.length == grid[i].length
        // 2. 2 <= n <= 100
        // 3. grid[i][j] is either 0 or 1.
        // 4. There are exactly two islands in grid.
        final int n = grid.length;
        final boolean[][] mark = new boolean[n][n];
        final Queue<int[]> queue = new LinkedList<>();
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                search(mark, grid, i, j, queue);
                break outer;
            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            result++;

            for (int t = 0, size = queue.size(); t < size; t++) {
                final int[] candidate = queue.poll();

                final int i = candidate[0];
                final int j = candidate[1];

                for (int[] direction : directions) {
                    final int ni = i + direction[0];
                    if (ni < 0 || ni >= n) continue;
                    final int nj = j + direction[1];
                    if (nj < 0 || nj >= n) continue;
                    
                    if (mark[ni][nj] == true) continue;
                    if (grid[ni][nj] == 1) return result - 1;

                    mark[ni][nj] = true;
                    queue.add(new int[]{ni, nj});
                }
            } 
        }

        return -1;
    }

    private void search(boolean[][] mark, int[][] grid, int i, int j, Queue<int[]> queue) {
        if (i < 0 || i >= mark.length) return;
        if (j < 0 || j >= mark.length) return;
        if (mark[i][j]) return;
        if (grid[i][j] == 0) return;
        
        mark[i][j] = true;
        queue.add(new int[]{i, j});
        for (int[] direction : directions) {
            search(mark, grid, i + direction[0], j + direction[1], queue);
        }
    }

    public static void main(String[] args) {
        Q934 q934 = new Q934();
        System.out.println(q934.shortestBridge(new int[][]{
            new int[]{0, 1, 0},
            new int[]{0, 0, 0},
            new int[]{0, 0, 1}
        }));

        System.out.println(q934.shortestBridge(new int[][]{
            new int[]{1, 0},
            new int[]{0, 1}
        }));
    }
}
