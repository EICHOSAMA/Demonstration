package per.eicho.demo.leetcode.q1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>1380. Lucky Numbers in a Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lucky-numbers-in-a-matrix/">
 *   1380. Lucky Numbers in a Matrix</a>
 */
public final class Q1380 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        // 1. m == mat.length
        // 2. n == mat[i].length
        // 3. 1 <= n, m <= 50
        // 4. 1 <= matrix[i][j] <= 10^5.
        // 5. All elements in the matrix are distinct.        
        final List<Integer> result = new ArrayList<>();

        // A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
        // 1. min element in row
        // 2. max element in col
        final int m = matrix.length;
        final int n = matrix[0].length;
        int[] rowMinElements = new int[m];
        Arrays.fill(rowMinElements, Integer.MAX_VALUE);
        int[] colMaxElements = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int num = matrix[i][j];
                rowMinElements[i] = Math.min(rowMinElements[i], num);
                colMaxElements[j] = Math.max(colMaxElements[j], num);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final int num = matrix[i][j];
                if (num == rowMinElements[i] && num == colMaxElements[j]) result.add(num);
            }
        }

        return result;
    }
}
