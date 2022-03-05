package per.eicho.demo.leetcode.q701_800;

/**
 * <p>799. Champagne Tower 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/champagne-tower/">
 *   799. Champagne Tower</a>
 */
public final class Q799 {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // 1. 0 <= poured <= 10^9
        // 2. 0 <= query_glass <= query_row < 100
        final double[][] remain = new double[101][101];
        remain[0][0] = poured;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                if (remain[i][j] <= 1.0) continue;

                remain[i + 1][j] += (remain[i][j] - 1) / 2;
                remain[i + 1][j + 1] += (remain[i][j] - 1) / 2;
                remain[i][j] = 1;
            }
        }
        return remain[query_row][query_glass];
    }
}
