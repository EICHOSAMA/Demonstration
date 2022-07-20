package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1277. Count Square Submatrices with All Ones 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-square-submatrices-with-all-ones/">
 *   1277. Count Square Submatrices with All Ones</a>
 */
public final class Q1277 {
    public int countSquares(int[][] matrix) {
        // 1. 1 <= matrix.length <= 300
        // 2. 1 <= matrix[0].length <= 300
        // 3. 0 <= matrix[i][j] <= 1
        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[][] sumMat = new int[m][n];

        int[] sumRow = new int[n];
        for (int i = 0; i < m; i++) {
            final int[] row = sumMat[i];
            int sum = 0;
            for (int j = 0; j < n; j++) {
                row[j] = sumRow[j] += sum += matrix[i][j];
            }
        }

        int result = matrix[m - 1][n - 1];
        System.out.println(result);
        for (int edgeLen = 2, maxLen = Math.min(m, n); edgeLen <= maxLen; edgeLen++) {
            final int target = edgeLen * edgeLen;
            for (int x1 = 0, boundX = m - edgeLen; x1 <= boundX; x1++) {
                final int x2 = x1 + edgeLen - 1;
                for (int y1 = 0, boundY = n - edgeLen; y1 <= boundY; y1++) {
                    final int y2 = y1 + edgeLen - 1;
                    if (sum(sumMat, x1, y1, x2, y2) == target) result++;
                }
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
