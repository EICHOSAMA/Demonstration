package per.eicho.demo.leetcode.q201_300;

/**
 * <p>240. Search a 2D Matrix II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/search-a-2d-matrix-ii/">
 *   240. Search a 2D Matrix II</a>
 */
public final class Q240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 1. m == matrix.length
        // 2. n == matrix[i].length
        // 3. 1 <= n, m <= 300
        // 4. -10^9 <= matrix[i][j] <= 10^9
        // 5. All the integers in each row are sorted in ascending order.
        // 6. All the integers in each column are sorted in ascending order.
        // 7. -10^9 <= target <= 10^9
        final int m = matrix.length;
        final int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            final int num = matrix[i][j];
            if (target == num) return true;
            if (target < num) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
