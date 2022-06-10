package per.eicho.demo.leetcode.q701_800;

import java.util.Arrays;

/**
 * <p>712. Minimum ASCII Delete Sum for Two Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/">
 *   712. Minimum ASCII Delete Sum for Two Strings</a>
 */
public final class Q712 {
    public int minimumDeleteSum(String s1, String s2) {
        // 1. 1 <= s1.length, s2.length <= 1000
        // 2. s1 and s2 consist of lowercase English letters.        
        final int n = s1.length();
        final int m = s2.length();
        final int[][] f = new int[n + 1][m + 1];
        for (int[] row : f) Arrays.fill(row, Integer.MAX_VALUE);
        f[0][0] = 0;
        for (int i = 0; i < n; i++) f[i + 1][0] = f[i][0] + s1.charAt(i);
        for (int i = 0; i < m; i++) f[0][i + 1] = f[0][i] + s2.charAt(i);
    
        for (int i = 1; i <= n; i++) {
            final char ch1 = s1.charAt(i - 1);

            for (int j = 1; j <= m; j++) {
                final char ch2 = s2.charAt(j - 1);
                if (ch1 == ch2) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                f[i][j] = Math.min(f[i][j], f[i - 1][j] + ch1);
                f[i][j] = Math.min(f[i][j], f[i][j - 1] + ch2);
            }
        }

        return f[n][m];
    }
}
