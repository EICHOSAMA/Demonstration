package per.eicho.demo.leetcode.q1201_1300;

import java.util.Arrays;

/**
 * <p>1293. Shortest Path in a Grid with Obstacles Elimination 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/">
 *   1293. Shortest Path in a Grid with Obstacles Elimination</a>
 */
public final class Q1293 {

    int[][][] memo;
    boolean[][][] marks;
    private static final int[][] directions = new int[][]{
        new int[]{0, -1}, new int[]{-1, 0},
        new int[]{0, 1} , new int[]{ 1, 0}
    };

    int m;
    int n;
    int[][] grid;
    int result = Integer.MAX_VALUE;

    public int shortestPath(int[][] grid, int k) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 40
        // 4. 1 <= k <= m * n
        // 5. grid[i][j] is either 0 or 1.
        // 6. grid[0][0] == grid[m - 1][n - 1] == 0
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        memo = new int[k + 1][m][n];
        marks = new boolean[k + 1][m][n];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], Integer.MAX_VALUE);
            }
        }
        
        if (k - grid[m - 1][n - 1] >= 0) {
            search(k - grid[m - 1][n - 1], m - 1, n - 1, 0);
        } else {
            return -1;
        }
        
        
        for (int i = 0; i <= k; i++) {
            result = Math.min(result, memo[i][0][0]);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void search(int k, int i, int j, int step) {
        if (step >= result) return;
        if (step >= memo[k][i][j]) return;
        // step < memo[k][i][j];
        memo[k][i][j] = step;
        if (i == 0 && j == 0) {
            result = Math.min(result, step);
            return;
        }
        marks[k][i][j] = true;

        for (int[] direction : directions) {
            final int ni = i + direction[0];
            if (ni < 0 || ni >= m) continue;
            final int nj = j + direction[1];
            if (nj < 0 || nj >= n) continue;

            final int val = grid[ni][nj];
            if (k - val >= 0 && !marks[k - val][ni][nj]) {
                search(k - val, ni, nj, step + 1);
            }
        }

        marks[k][i][j] = false;
    }

    public static void main(String[] args) {
        Q1293 q1293 = new Q1293();
        System.out.println(q1293.shortestPath(new int[][]{
            new int[]{0, 0, 0},
            new int[]{1, 1, 0},
            new int[]{0, 0, 0},
            new int[]{0, 1, 1},
            new int[]{0, 0, 0}
        }, 1));
    }
}
