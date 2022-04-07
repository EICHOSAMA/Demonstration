package per.eicho.demo.leetcode.q601_700;

/**
 * <p>647. Palindromic Substrings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/palindromic-substrings/">647. Palindromic Substrings</a>
 */
public final class Q647 {
    public int countSubstrings(String s) {
        // 1. 1 <= s.length <= 1000
        // 2. s consists of lowercase English letters.        
        final int n = s.length();
        final boolean[][] f = new boolean[n][n];

        int result = 0;
        for (int i = 0; i < n; i++) f[i][i] = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) f[i][i + 1] = true;
        }

        for (int len = 1; len < n; len++) {
            int i = 1, j = i + len - 1;
            while (j < n - 1) {
                if (f[i][j]) {
                    if (s.charAt(i - 1) == s.charAt(j + 1)) f[i - 1][j + 1] = true;
                }
                i++; j++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) if (f[i][j]) result++;
        }

        return result;
    }

    public static void main(String[] args) {
        Q647 q647 = new Q647();
        System.out.println(q647.countSubstrings("aaa"));
        System.out.println(q647.countSubstrings("a"));
    }
}
