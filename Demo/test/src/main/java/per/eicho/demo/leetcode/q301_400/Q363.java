package per.eicho.demo.leetcode.q301_400;

/**
 * <p>363. Max Sum of Rectangle No Larger Than K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/">
 *   363. Max Sum of Rectangle No Larger Than K</a>
 */
public final class Q363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= m, n <= 100
        // 4. -100 <= matrix[i][j] <= 100
        // 5. -10^5 <= k <= 10^5
        final int m = matrix.length;    // row nums;
        final int n = matrix[0].length; // col nums
        final int[][] sumInfo = genSumInfo(matrix);
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = i; x < m; x++) {
                    for (int y = j; y < n; y++) {
                        final int sum = getSum(sumInfo, i, j, x, y);
                        if (sum <= k) maxSum = Math.max(maxSum, sum);
                    }
                }
            }
        }
        return maxSum;
    }

    private int[][] genSumInfo(int[][] matrix) {
        final int m = matrix.length;    // row nums;
        final int n = matrix[0].length; // col nums        
        final int[][] sumInfo = new int[m][n];

        final int[][] rowSumInfo = new int[m][n];
        for (int i = 0; i < m; i++) {
            final int[] row = matrix[i];
            final int[] rowSum = rowSumInfo[i];

            rowSum[0] = row[0];
            for (int j = 1; j < n; j++) rowSum[j] = rowSum[j - 1] + row[j];
        }

        for (int i = 0; i < n; i++) {
            sumInfo[0][i] = rowSumInfo[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sumInfo[i][j] = sumInfo[i - 1][j] + rowSumInfo[i][j];
            }
        }

        return sumInfo;
    }

    private int getSum(int[][] sumInfo, int x1, int y1, int x2, int y2) {
        int sum = sumInfo[x2][y2];
        if (y1 - 1 >= 0) sum -= sumInfo[x2][y1 -1];
        if (x1 - 1 >= 0) sum -= sumInfo[x1 - 1][y2];
        if (x1 - 1 >= 0 && y1 -1 >= 0) sum += sumInfo[x1 - 1][y1 - 1];
        return sum;
    }
}
