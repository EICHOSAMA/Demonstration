package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1582. Special Positions in a Binary Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/special-positions-in-a-binary-matrix/">
 *   1582. Special Positions in a Binary Matrix</a>
 */
public final class Q1582 {
    public int numSpecial(int[][] mat) {
        // 1. m == mat.length
        // 2. n == mat[i].length
        // 3. 1 <= m, n <= 100
        // 4. mat[i][j] is either 0 or 1.        
        final int m = mat.length;
        final int n = mat[0].length;
        int result = 0;

        final int[] rowSum = new int[m];
        final int[] colSum = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int num = mat[i][j];
                rowSum[i] += num;
                colSum[j] += num;
            }
        }

        for (int i = 0; i < m; i++) {
            if (rowSum[i] != 1) continue;
            for (int j = 0; j < n; j++) {
                if (colSum[j] != 1) continue;
                if (mat[i][j] != 1) continue;
                result++;
            }
        }

        return result;
    }
}
