package per.eicho.demo.leetcode.q1501_1600;

import java.util.Arrays;

/**
 * <p>1504. Count Submatrices With All Ones 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-submatrices-with-all-ones/">
 *   1504. Count Submatrices With All Ones</a>
 */
public final class Q1504 {
    public int numSubmat(int[][] mat) {
        // 1. 1 <= m, n <= 150
        // 2. mat[i][j] is either 0 or 1.
        final int m = mat.length;
        final int n = mat[0].length;

        int result = 0;
        int[] sums = new int[n];
        for (int top = 0; top < m; top++) {
            Arrays.fill(sums, 0);
            for (int bot = top; bot < m; bot++) {
                final int h = bot - top + 1;
                for (int y = 0; y < n; y++) sums[y] += mat[bot][y];

                // sliding window
                int l = 0, r = 0; // [l, r)
                while (l < n) {
                    while (l < n && sums[l] != h) l++;
                    if (l == n) break;

                    // sums[l] == h
                    r = l + 1;
                    while (r < n && sums[r] == h) r++;

                    final int len = r - l;
                    result += ((len + 1) * len) / 2;

                    l = r;
                }
            }
        }

        return result;
    }
}
