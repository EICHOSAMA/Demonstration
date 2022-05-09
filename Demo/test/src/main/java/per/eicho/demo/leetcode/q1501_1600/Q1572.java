package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1572. Matrix Diagonal Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/matrix-diagonal-sum/">
 *   1572. Matrix Diagonal Sum</a>
 */
public final class Q1572 {
    public int diagonalSum(int[][] mat) {
        // 1. n == mat.length == mat[i].length
        // 2. 1 <= n <= 100
        // 3. 1 <= mat[i][j] <= 100        
        final int n = mat.length;
        int sum = 0;
        int l = 0, r = n - 1;
        for (int i = 0; i < n; i++) {
            final int[] row = mat[i];
            sum += row[l++] + row[r--];
        }
        return sum - ((n % 2 == 0) ? 0 : mat[n / 2][n / 2]);
    }
}
