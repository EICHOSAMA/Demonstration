package per.eicho.demo.leetcode.q1301_1400;

import java.util.Arrays;

/**
 * <p>1312. Minimum Insertion Steps to Make a String Palindrome 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/">
 *   1312. Minimum Insertion Steps to Make a String Palindrome</a>
 */
public final class Q1312 {
    public int minInsertions(String s) {
        // 1. 1 <= s.length <= 500
        // 2. s consists of lowercase English letters.
        final int n = s.length();
        final int[][] f = new int[n][n];
        for (int[] row : f) Arrays.fill(row, n);
        for (int i = 0; i < n; i++) f[i][i] = 0;
        for (int i = n - 1; i > 0; i--) {
            if (s.charAt(i - 1) == s.charAt(i)) f[i - 1][i] = 0;
        }

        for (int len = 1; len <= n; len++) {
            int l = 0, r = l + len - 1; // [l, r]
            while (r < n) {
                final int val = f[l][r];
                if (l - 1 >= 0) {
                    f[l - 1][r] = Math.min(f[l - 1][r], val + 1);
                }

                if (r + 1 < n) {
                    f[l][r + 1] = Math.min(f[l][r + 1], val + 1);
                }

                if (l - 1 >= 0 && r + 1 < n) {
                    if (s.charAt(l - 1) == s.charAt(r + 1)) {
                        f[l - 1][r + 1] = Math.min(f[l - 1][r + 1], val);
                    }
                }

                l++; r++;
            }
        }

        return f[0][n - 1];
    }

    public static void main(String[] args) {
        Q1312 q1312 = new Q1312();
        System.out.println(q1312.minInsertions("zzazz"));
        System.out.println(q1312.minInsertions("mbadm"));
        System.out.println(q1312.minInsertions("leetcode"));
        System.out.println(q1312.minInsertions("a"));
        
    }
}
