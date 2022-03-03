package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1252. Cells with Odd Values in a Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/">
 *   1252. Cells with Odd Values in a Matrix</a>
 */
public final class Q1252 {
    public int oddCells(int m, int n, int[][] indices) {
        // 1. 1 <= m, n <= 50
        // 2. 1 <= indices.length <= 100
        // 3. 0 <= ri < m
        // 4. 0 <= ci < n        
        final int[] rowCount = new int[m];
        final int[] colCount = new int[n];

        for (int[] pair : indices) {
            rowCount[pair[0]]++;
            colCount[pair[1]]++;
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            final int rc = rowCount[i];
            for (int j = 0; j < n; j++) {
                if ((rc + colCount[j]) % 2 != 0) result++;
            }
        }

        return result;
    }
}
