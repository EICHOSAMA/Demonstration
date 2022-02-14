package per.eicho.demo.leetcode.q001_100;

/**
 * <p>62. Unique Paths 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-paths/">62. Unique Paths</a>
 */
public final class Q62 {
    public int uniquePaths(int m, int n) {
        // 1. 1 <= m, n <= 100
        if (m > n) {
            // swap m & n.
            m ^= n;
            n ^= m;
            m ^= n;
        }
        //assert m < n: "Optimize Memory Cost";

        if (m == 0) throw new IllegalArgumentException();
        //assert m != 0: "Checked";

        // n x m matrix -> 2 x m matrix
        int[][] f = new int[2][m];
        // F[0, 0] = 1.
        f[0][0] = 1;

        int row = 0; // representing the index of processing row.
        while (row < n) {
            int i = row++ % 2;
            int iM1 = i ^ 1; // iM1 means: i - 1

            f[i][0] = 1;
            for (int j = 1; j < m; j++) {
                // F[i, j] = F[i - 1, j] + F[i, j - 1]
                f[i][j] = f[iM1][j] + f[i][j - 1];
            }
        }
        //assert row == n;
        return f[(n - 1) % 2][m - 1];    
    }
}
