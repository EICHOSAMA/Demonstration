package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1314. Matrix Block Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/matrix-block-sum/">
 *   1314. Matrix Block Sum</a>
 */
public final class Q1314 {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        // 1. m == mat.length
        // 2. n == mat[i].length
        // 3. 1 <= m, n, k <= 100
        // 4. 1 <= mat[i][j] <= 100
        final int m = mat.length;
        final int n = mat[0].length;
        final int[][] sumMat = new int[m][n];
        int[] sums = new int[n];
        for (int i = 0; i < m; i++) {
            final int[] row = sumMat[i];

            int sum = 0;
            for (int j = 0; j < n; j++) {
                final int num = mat[i][j];
                sums[j] += num;
                row[j] = sum += sums[j];
            }
        } 

        final int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int x1 = Math.max(i - k, 0);
                final int y1 = Math.max(j - k, 0);
                final int x2 = Math.min(i + k, m -1);
                final int y2 = Math.min(j + k, n - 1);

                result[i][j] = sum(sumMat, x1, y1, x2, y2);
            }
        }

        return result;
    }

    private int sum(int[][] sumMat, int x1, int y1, int x2, int y2) {
        final int area1 = sumMat[x2][y2];
        final int area2 = x1 == 0 ? 0 : sumMat[x1 - 1][y2];
        final int area3 = y1 == 0 ? 0 : sumMat[x2][y1 - 1];
        final int area4 = x1 * y1 == 0 ? 0 : sumMat[x1 - 1][y1 - 1];
        return area1 + area4 - area2 - area3;
    }
}
