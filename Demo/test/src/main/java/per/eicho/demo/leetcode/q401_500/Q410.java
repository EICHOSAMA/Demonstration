package per.eicho.demo.leetcode.q401_500;

import java.util.Arrays;

/**
 * <p>410. Split Array Largest Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/split-array-largest-sum/">410. Split Array Largest Sum</a>
 */
public final class Q410 {
    public int splitArray(int[] nums, int m) {
        // 1. 1 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 10^6
        // 3. 1 <= m <= min(50, nums.length)        
        final int n = nums.length;
        final int[][] f = new int[m][n];
        for (int[] row : f) { Arrays.fill(row, Integer.MAX_VALUE); }

        f[0][0] = nums[0];
        for (int i = 1; i < n; i++) f[0][i] = f[0][i - 1] + nums[i];

        for (int i = 0; i < m - 1; i++) {
            for (int j = i; j < n; j++) {
                final int fij = f[i][j];
                for (int k = j + 1; k < n; k++) f[i + 1][k] = Math.min(f[i + 1][k], Math.max(fij, sum(f[0], j + 1, k)));    
            }
        }
        return f[m - 1][n - 1];
    }

    private int sum(int[] sum, int l, int r) {
        return sum[r] - sum[l - 1];
    }
}
