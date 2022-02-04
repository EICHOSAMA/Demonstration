package per.eicho.demo.leetcode.q101_200;

/**
 * <p>200. Number of Islands 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-islands/">200. Number of Islands</a>
 */
public final class Q200 {

    int m;
    int n;

    public int numIslands(char[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 300
        // 4. grid[i][j] is '0' or '1'.
        m = grid.length;
        n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            final char[] row = grid[i];

            for (int j = 0; j < n; j++) {
                final char cell = row[j];

                if (cell == '1') {
                    count++;
                    process(grid, i, j);
                }
            }
        }
        return count;
    }

    private void process(char[][] grid, int i, int j) {
        if (i < 0 || i >= m) return;
        if (j < 0 || j >= n) return;
        if (grid[i][j] != '1') return;

        grid[i][j] = '0';
        process(grid, i - 1, j);
        process(grid, i + 1, j);
        process(grid, i, j - 1);
        process(grid, i, j + 1);
    }
}
