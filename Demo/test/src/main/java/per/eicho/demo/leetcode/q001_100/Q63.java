package per.eicho.demo.leetcode.q001_100;

/**
 * <p>63. Unique Paths II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-paths-ii/">63. Unique Paths II</a>
 */
public final class Q63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 1. m == obstacleGrid.length
        // 2. n == obstacleGrid[i].length
        // 3. 1 <= m, n <= 100
        // 4. obstacleGrid[i][j] is 0 or 1.
        
        final int OBSTACLE = 1;
        // Fix: for wrong test case. [{1}] -> 1.
        if (obstacleGrid[0][0] == OBSTACLE) return 0;

        final int row = obstacleGrid.length;
        final int col = obstacleGrid[0].length;

        int[][] f = new int[row][col];
        f[0][0] = 1;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] != OBSTACLE) {
                    if (i-1 >= 0)
                        f[i][j] += f[i-1][j];
                    if (j-1 >= 0)
                        f[i][j] += f[i][j-1];
                }
            }
        }
        return f[row - 1][col - 1];        
    }
}
