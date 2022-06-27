package per.eicho.demo.leetcode.q1001_1100;

import java.util.Arrays;

/**
 * <p>1027. Longest Arithmetic Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/divisor-game/">
 *   1027. Longest Arithmetic Subsequence</a>
 */
public final class Q1027 {
    public int longestArithSeqLength(int[] nums) {
        // 1. 2 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 500
        final int n = nums.length;
        final int[][] f = new int[n][1001]; // [-500, 500]
        for (int[] row : f) Arrays.fill(row, 1);
        final int offset = 500;
        int result = 1;
        for (int i = n - 1; i >= 0; i--) {
            final int num = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                final int numJ = nums[j];
                final int diff = numJ - num;
                final int idx = diff + offset;
                
                f[j][idx] = Math.max(f[j][idx], f[i][idx] + 1);

                result = Math.max(result, f[j][idx]);
            }
        }
        return result;
    }
}
