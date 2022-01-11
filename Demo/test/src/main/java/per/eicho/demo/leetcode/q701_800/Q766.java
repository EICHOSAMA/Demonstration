package per.eicho.demo.leetcode.q701_800;

/**
 * <p>766. Toeplitz Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/toeplitz-matrix/">766. Toeplitz Matrix</a>
 */
public final class Q766 {

    static final int CHECKED_MARK = -1;

    public boolean isToeplitzMatrix(int[][] matrix) {
        // 0 <= matrix[i][j] <= 99
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        for (int col = 0; col < colNum; col++) {
            for (int row = 0; row < rowNum; row++) {
                if (matrix[row][col] == CHECKED_MARK) continue;

                final int num = matrix[row][col];
                int rowP = row;
                int colP = col;

                while (rowP < rowNum && colP < colNum) {
                    if (matrix[rowP][colP] != num) return false;
                    matrix[rowP][colP] = CHECKED_MARK;
                    rowP++; colP++;
                }

            }
        }

        return true;
    }
}
