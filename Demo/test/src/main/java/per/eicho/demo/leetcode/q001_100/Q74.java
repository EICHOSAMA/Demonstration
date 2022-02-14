package per.eicho.demo.leetcode.q001_100;

/**
 * <p>74. Search a 2D Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix/">74. Search a 2D Matrix</a>
 */
public final class Q74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= m, n <= 100
        // 4. -10^4 <= matrix[i][j], target <= 10^4        
        final int rowCount = matrix.length;
        if (rowCount == 0) return false;
        final int colCount = matrix[0].length;
        if (colCount == 0) return false;

        int row = searchRow(matrix, target, 0, rowCount - 1);
        if (row == -1) return false; // -1 means not found a possible row containing target.
        int col = searchCol(matrix[row], target, 0, colCount - 1);
        return col != -1;
    }

    private int searchRow(final int[][] matrix, final int target, final int l, final int r) {
        if (l == r) {
            if (target < matrix[l][0] || target > matrix[l][matrix[l].length - 1]) return -1; // out of range
            return l;
        }

        final int mid = (l + r + 1) / 2;
        final int midValue = matrix[mid][0];

        if (target < midValue)
            return searchRow(matrix, target, l, mid - 1); // search top
        return searchRow(matrix, target, mid, r);// search bottom
    }

    private int searchCol(final int[] row, final int target, final int l, final int r) {
        if (l == r) {
            return row[l] == target? l: -1;
        }

        final  int mid = (l + r + 1) / 2;
        final  int midValue = row[mid];

        if (target < midValue)
            return searchCol(row, target, l, mid - 1); // search left
        return searchCol(row, target, mid, r);// search right
    }
}
