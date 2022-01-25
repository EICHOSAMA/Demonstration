package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2133. Check if Every Row and Column Contains All Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/">2133. Check if Every Row and Column Contains All Numbers</a>
 */
public final class Q2133 {
    public boolean checkValid(int[][] matrix) {
        final int n = matrix.length;

        final int[] row = new int[n + 1];
        for (int i = 0; i < n; i++) {
            final int target = i + 1;
            for (int j = 0; j < n; j++) {
              if (++row[matrix[i][j]] != target) return false;
            }
        }

        final int[] col = new int[n + 1];
        for (int i = 0; i < n; i++) {
            final int target = i + 1;
            for (int j = 0; j < n; j++) {
              if (++col[matrix[j][i]] != target) return false;
            }
        }

        return true;
    }
}
