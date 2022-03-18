package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1351. Count Negative Numbers in a Sorted Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/">
 *   1351. Count Negative Numbers in a Sorted Matrix</a>
 */
public final class Q1351 {
    public int countNegatives(int[][] grid) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m, n <= 100
        // 4. -100 <= grid[i][j] <= 100        
        final int m = grid.length;
        final int n = grid[0].length;

        int result = 0;
        int j = 0;
        for (int i = m - 1; i >= 0; i--) {
            final int[] row = grid[i];
            while (j < n && row[j] >= 0) j++;
            result += (n - j);
        }

        return result;
    }
}
