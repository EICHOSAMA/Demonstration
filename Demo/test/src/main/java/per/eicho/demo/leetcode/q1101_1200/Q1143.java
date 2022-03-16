package per.eicho.demo.leetcode.q1101_1200;

/**
 * <p>1143. Longest Common Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-common-subsequence/">
 *   1143. Longest Common Subsequence</a>
 */
public final class Q1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        // 1. 1 <= text1.length, text2.length <= 1000
        // 2. text1 and text2 consist of only lowercase English characters.        
        final int len1 = text1.length();
        final int len2 = text2.length();
        final int[][] f = new int[len1 + 1][len2 + 1];
        
        for (int i = 0; i < len1; i++) {
            final char ch1 = text1.charAt(i);
            for (int j = 0; j < len2; j++) {
                if (ch1 == text2.charAt(j)) {
                    f[i + 1][j + 1] = f[i][j] + 1; 
                } else {
                    f[i + 1][j + 1] = Math.max(f[i][j + 1], f[i + 1][j]);
                }
            }
        }

        return f[len1][len2];
    }

    public static void main(String[] args) {
        Q1143 q1143 = new Q1143();
        System.out.println(q1143.longestCommonSubsequence("abcde", "ace"));
    }
}
