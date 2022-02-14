package per.eicho.demo.leetcode.q001_100;

/**
 * <p>73. Set Matrix Zeroes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/set-matrix-zeroes/">73. Set Matrix Zeroes</a>
 */
public final class Q73 {
    public void setZeroes(int[][] matrix) {
        // 1. m == matrix.length
        // 2. n == matrix[0].length
        // 3. 1 <= m, n <= 200
        // 4. -2^31 <= matrix[i][j] <= 2^31 - 1        
        final int rowCount = matrix.length;
        final int colCount = matrix[0].length;

        // 1. generate two table.
        final boolean[] rowContainZero = new boolean[rowCount];
        final boolean[] colContainZero = new boolean[colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (matrix[i][j] == 0) {
                    rowContainZero[i] = true;
                    colContainZero[j] = true;
                }
            }
        }

        // 2. do in-place replacement.
        for (int i = 0; i < rowCount; i++) {
            if (rowContainZero[i] == true) {
                for (int j = 0; j < colCount; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < colCount; j++) {
            if (colContainZero[j] == true) {
                for (int i = 0; i < rowCount; i++) {
                    matrix[i][j] = 0;
                }
            }
        }        
    }
}
