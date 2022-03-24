package per.eicho.demo.leetcode.q601_700;

/**
 * <p>695. Max Area of Island 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-area-of-island/">695. Max Area of Island</a>
 */
public final class Q695 {
    public int maxAreaOfIsland(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 50
        // 4. grid[i][j] is either 0 or 1.
        final int m = grid.length;
        final int n = grid[0].length;
        
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    final int count = search(grid, i, j);
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }

    private int search(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length) return 0;
        if (j < 0 || j >= grid[0].length) return 0;
        if (grid[i][j] != 1) return 0;

        int count = 1;
        grid[i][j] = -1;

        count += search(grid, i + 1, j);
        count += search(grid, i - 1, j);
        count += search(grid, i, j + 1);
        count += search(grid, i, j - 1);

        return count;
    }
}
