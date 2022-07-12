package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>931. Minimum Falling Path Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-falling-path-sum/">
 *   931. Minimum Falling Path Sum</a>
 */
public final class Q931 {
    public int minFallingPathSum(int[][] matrix) {
        // 1. n == matrix.length == matrix[i].length
        // 2. 1 <= n <= 100
        // 3. -100 <= matrix[i][j] <= 100
        final int n = matrix.length;
        if (n == 1) return matrix[0][0];
        
        final int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) f[0][i] = matrix[0][i];
        for (int i = 1; i < n; i++) {
            final int[] prevRow = f[i - 1];
            final int[] row = f[i];
            
            final int[] mRow = matrix[i];

            row[0] = mRow[0] + Math.min(prevRow[0], prevRow[1]);
            row[n - 1] = mRow[n - 1] + Math.min(prevRow[n - 1], prevRow[n - 2]);

            for (int j = 1, bound = n - 1; j < bound; j++) {
                row[j] = mRow[j] + Math.min(prevRow[j - 1], Math.min(prevRow[j], prevRow[j + 1]));
            }
        }

        int result = Integer.MAX_VALUE;
        for (int candidate : f[n - 1]) result = Math.min(result, candidate);
        return result;
    }
}
