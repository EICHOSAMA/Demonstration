package per.eicho.demo.leetcode.q301_400;

/**
 * <p>329. Longest Increasing Path in a Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/">329. Longest Increasing Path in a Matrix</a>
 */
public final class Q329 {

    int[][] memo;
    int m;
    int n;

    public int longestIncreasingPath(int[][] matrix) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= m, n <= 200
        // 4. 0 <= matrix[i][j] <= 2^31 - 1
        m = matrix.length;
        n = matrix[0].length;
        memo = new int[m][n];

        int result = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] == 0) {
                    memo[i][j] = dfs(matrix, i, j);
                    result = Math.max(result, memo[i][j]);
                }
            }
        }
        return result;
    }

    private int dfs(int[][] matrix, int i, int j) {
        if (memo[i][j] != 0) return memo[i][j];
        final int current = matrix[i][j];

        // i + 1, j
        int maxLength = 1;
        if (i + 1 < m && matrix[i + 1][j] > current) {
            maxLength = Math.max(maxLength, dfs(matrix, i + 1, j) + 1);
        }

        // i - 1, j
        if (i - 1 >= 0 && matrix[i - 1][j] > current) {
            maxLength = Math.max(maxLength, dfs(matrix, i - 1, j) + 1);
        }

        // i, j + 1
        if (j + 1 < n && matrix[i][j + 1] > current) {
            maxLength = Math.max(maxLength, dfs(matrix, i, j + 1) + 1);
        }        

        // i, j - 1
        if (j - 1 >= 0 && matrix[i][j - 1] > current) {
            maxLength = Math.max(maxLength, dfs(matrix, i, j - 1) + 1);
        }

        memo[i][j] = maxLength;
        return maxLength;
    }
}
