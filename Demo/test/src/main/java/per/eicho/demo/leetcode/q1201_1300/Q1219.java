package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1219. Path with Maximum Gold 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/path-with-maximum-gold/">
 *   1219. Path with Maximum Gold</a>
 */
public final class Q1219 {

    int m;
    int n;
    boolean[][] mark;
    private static final int[][] directions = new int[][]{
        new int[]{0, 1}, new int[]{0, -1},
        new int[]{1, 0}, new int[]{-1, 0}
    };

    public int getMaximumGold(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 15
        // 4. 0 <= grid[i][j] <= 100
        // 5. There are at most 25 cells containing gold.
        m = grid.length;
        n = grid[0].length;
        mark = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                mark[i][j] = true;
                result = Math.max(result, search(grid, i, j));
                mark[i][j] = false;
            }
        }

        return result;
    }

    private int search(int[][] grid, int x, int y) {
        int result = 0;
        for (int[] direction : directions) {
            final int nx = x + direction[0];
            if (nx < 0 || nx >= m) continue;
            final int ny = y + direction[1];
            if (ny < 0 || ny >= n) continue;

            if (grid[nx][ny] == 0) continue;
            if (mark[nx][ny]) continue;

            mark[nx][ny] = true;
            result = Math.max(result, search(grid, nx, ny));
            mark[nx][ny] = false;
        }

        return result + grid[x][y];
    }
}
