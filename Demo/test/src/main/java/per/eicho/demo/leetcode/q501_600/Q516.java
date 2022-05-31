package per.eicho.demo.leetcode.q501_600;

/**
 * <p>516. Longest Palindromic Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">
 *   516. Longest Palindromic Subsequence</a>
 */
public final class Q516 {
    public int longestPalindromeSubseq(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s consists only of lowercase English letters.        
        final int n = s.length();
        final int[][] f = new int[n][n];

        // 1. f[i][i] = 1
        for (int i = 0; i < n; i++) f[i][i] = 1;
        // 2. s[i] == s[i + 1] f[i][i + 1] = s[i] == s[i + 1] ? 2 : 1
        for (int i = 0; i < n - 1; i++) f[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1; 
        // 3. f[i][j] = Max(f[i][j - 1], f[i + 1][j], f[i + 1][j - 1] + (s[i] == s[j] ? 2 : 0))
        for (int len = 3; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                f[i][j] = Math.max(f[i][j - 1], Math.max(f[i + 1][j], f[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 2 : 0)));
            }
        }
        return f[0][n - 1];
    }

    public static void main(String[] args) {
        Q516 q516 = new Q516();
        System.out.println(q516.longestPalindromeSubseq("bbbab"));
    }
}
