package per.eicho.demo.leetcode.q801_900;

/**
 * <p>867. Transpose Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/transpose-matrix/">867. Transpose Matrix</a>
 */
public final class Q867 {
    public int[][] transpose(int[][] matrix) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= m, n <= 1000
        // 4. 1 <= m * n <= 10^5
        // 5. -10^9 <= matrix[i][j] <= 10^9
        final int m = matrix.length;
        final int n = matrix[0].length;        
        final int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }
}
