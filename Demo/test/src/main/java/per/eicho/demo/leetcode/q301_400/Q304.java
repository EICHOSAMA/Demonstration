package per.eicho.demo.leetcode.q301_400;

/**
 * <p>304. Range Sum Query 2D - Immutable 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/range-sum-query-2d-immutable/">
 *   304. Range Sum Query 2D - Immutable</a>
 */
@SuppressWarnings("unuse")
public final class Q304 {
    class NumMatrix {

        final int[][] sumInfo;
        final int m;
        final int n;

        /**
         * Initializes the object with the integer matrix matrix.
         */
        public NumMatrix(int[][] matrix) {
            // 1. m == matrix.length
            // 2. n == matrix[i].length
            // 3. 1 <= m, n <= 200
            // 4. -10^5 <= matrix[i][j] <= 10^5
            m = matrix.length;
            n = matrix[0].length;

            sumInfo = new int[m][n];

            int sum = 0;
            for (int i = 0; i < m; i++) {
                sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += matrix[i][j];
                    sumInfo[i][j] = sum;
                }
            }
        }
        
        /**
         * Returns the sum of the elements of matrix inside the rectangle 
         * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            // 1. 0 <= row1 <= row2 < m
            // 2. 0 <= col1 <= col2 < n
            // 3. At most 10^4 calls will be made to sumRegion.
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += (sumInfo[i][col2] - (col1 == 0 ? 0 : sumInfo[i][col1 - 1]));
            }
            return sum;
        }
    }
}
