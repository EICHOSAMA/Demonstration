package per.eicho.demo.leetcode.q101_200;

/**
 * <p>115. Distinct Subsequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/distinct-subsequences/">115. Distinct Subsequences</a>
 */
public final class Q115 {
    public int numDistinct(String s, String t) {
        // 1. 1 <= s.length, t.length <= 1000
        // 2. s and t consist of English letters.        
        final int n = s.length();
        final int m = t.length();
        if (m > n) return 0;

        int result = 0;
        final int[][] f = new int[n + 1][m + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            final int c1 = s.charAt(i);

            for (int j = 0; j < m; j++) {
                if (f[i][j] == 0) continue; // skip
                
                final int c2 = t.charAt(j);
                if (c1 == c2) {
                    f[i + 1][j + 1] += f[i][j];
                }

                f[i + 1][j] += f[i][j];
            }

            result += f[i][m];
        }
        result += f[n][m];
        return result;
    }
    
    public static void main(String[] args) {
        Q115 q115 = new Q115();
        System.out.println(q115.numDistinct("babgbag", "bag"));
    }
}
