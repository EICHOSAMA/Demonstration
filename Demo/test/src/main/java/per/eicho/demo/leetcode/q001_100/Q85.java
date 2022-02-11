package per.eicho.demo.leetcode.q001_100;

/**
 * <p>85. Maximal Rectangle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximal-rectangle/">85. Maximal Rectangle</a>
 */
public final class Q85 {
    public int maximalRectangle(char[][] matrix) {
        // 1. rows == matrix.length
        // 2. cols == matrix[i].length
        // 3. 1 <= row, cols <= 200
        // 4. matrix[i][j] is '0' or '1'.
        final int m = matrix.length;
        final int n = matrix[0].length;
        
        final int[][] max = new int[m][n];

        int result = 0;
        for (int i = 0; i < m; i++) {
            max[i][0] = matrix[i][0] == '1' ? 1 : 0;
            result = process(matrix, max, i, 0, result);

            for (int j = 1; j < n; j++) {
                max[i][j] = matrix[i][j] == '1' ? max[i][j - 1] + 1 : 0;
                result = process(matrix, max, i, j, result);
            }
        }
        return result;
    }

    private int process(char[][] matrix, int[][] max, int i, int j, int result) {
        int k = i;
        int width = Integer.MAX_VALUE;
        while (k >= 0 && max[k][j] != 0) {
            width = Math.min(width, max[k][j]);
            result = Math.max(result, width * (i - k + 1));
            k--;
        }
        return result;
    }
}
