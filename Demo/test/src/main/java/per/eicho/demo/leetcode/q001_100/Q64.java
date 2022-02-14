package per.eicho.demo.leetcode.q001_100;

/**
 * <p>64. Minimum Path Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-path-sum/">64. Minimum Path Sum</a>
 */
public final class Q64 {
    public int minPathSum(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 200
        // 4. 0 <= grid[i][j] <= 100        
        final int m = grid.length;
        final int n = grid[0].length;

        final int[][] f = new int[m][n];

        // 1. initialize f table.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
            }
        }
        f[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) f[0][i] = f[0][i-1] + grid[0][i];

        // 2. start dynamic programming.
        for (int i = 1; i < m; i++) { // m = 3 , 0 -> 0 < 3 -> 1 -> 1 < 3 -> 2 < 3 -> 3
            f[i][0] = f[i-1][0] + grid[i][0];

            for (int j = 1; j < n; j++)
                f[i][j] = Math.min(f[i-1][j], f[i][j-1]) + grid[i][j];
        }

        return f[m-1][n-1];        
    }
}
