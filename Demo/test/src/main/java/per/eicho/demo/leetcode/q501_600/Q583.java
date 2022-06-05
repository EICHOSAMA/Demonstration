package per.eicho.demo.leetcode.q501_600;

/**
 * <p>583. Delete Operation for Two Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/delete-operation-for-two-strings/">
 *   583. Delete Operation for Two Strings</a>
 */
public final class Q583 {
    public int minDistance(String word1, String word2) {
        // 1. 1 <= word1.length, word2.length <= 500
        // 2. word1 and word2 consist of only lowercase English letters.        
        final int n = word1.length();
        final int m = word2.length();
        final int[][] f = new int[n + 1][m + 1]; // longest common subsequence.
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }

        return m + n - f[n][m] * 2;
    }
}
