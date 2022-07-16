package per.eicho.demo.leetcode.q1001_1100;

/**
 * <p>1020. Number of Enclaves 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-enclaves/">
 *   1020. Number of Enclaves</a>
 */
public final class Q1020 {

    private static final int[][] directions = new int[][]{
        new int[]{-1, 0}, new int[]{1, 0},
        new int[]{0, 1}, new int[]{0, -1}
    };

    int m;
    int n;

    public int numEnclaves(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 500
        // 4. grid[i][j] is either 0 or 1.
        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) search(grid, 0, i);
            if (grid[m - 1][i] == 1) search(grid, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) search(grid, i, 0);
            if (grid[i][n - 1] == 1) search(grid, i, n - 1);
        }

        int result = 0;
        for (int[] row : grid) {
            for (int val : row) {
                result += val;
            }
        }

        return result;
    }

    private void search(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        for (int[] direction : directions) {
            final int ni = i + direction[0];
            if (ni < 0 || ni >= m) continue;
            final int nj = j + direction[1];
            if (nj < 0 || nj >= n) continue;
            
            if (grid[ni][nj] == 1) search(grid, ni, nj);
        }
    }
}
