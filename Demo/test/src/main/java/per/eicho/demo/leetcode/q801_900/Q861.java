package per.eicho.demo.leetcode.q801_900;

/**
 * <p>861. Score After Flipping Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/score-after-flipping-matrix/">
 *   861. Score After Flipping Matrix</a>
 */
public final class Q861 {
    public int matrixScore(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 20
        // 4. grid[i][j] is either 0 or 1.        
        final int m = grid.length;
        final int n = grid[0].length;

        for (int[] row : grid) {
            if (row[0] == 1) continue;
            for (int i = 0; i < n; i++) row[i] ^= 1;
        }

        final int[] sums = new int[n];
        for (int[] row : grid) {
            for (int i = 0; i < n; i++) sums[i] += row[i];
        }

        int result = 0;
        for (int i = n - 1, base = 1; i >= 0; i--, base <<= 1) {
            result += base * Math.max(m - sums[i], sums[i]);
        }
        return result;
    }
}
