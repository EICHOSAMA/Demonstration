package per.eicho.demo.leetcode.q701_800;

import java.util.Arrays;

/**
 * <p>764. Largest Plus Sign 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-plus-sign/">
 *   764. Largest Plus Sign</a>
 */
public final class Q764 {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        // 1. 1 <= n <= 500
        // 2. 1 <= mines.length <= 5000
        // 3. 0 <= xi, yi < n
        // 4. All the pairs (xi, yi) are unique.        
        final int[][] matrix = new int[n][n];
        for (int[] row : matrix) Arrays.fill(row, 1);
        for (int[] point : mines) matrix[point[0]][point[1]] = 0;
        final int[][] up = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    up[i][j] = (i - 1 >= 0 ? up[i - 1][j] + 1 : 1);
                }
            }
        }

        final int[][] down = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    down[i][j] = (i + 1 < n ? down[i + 1][j] + 1 : 1);
                }
            }
        }  

        final int[][] left = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    left[i][j] = (j - 1 >= 0 ? left[i][j - 1] + 1 : 1);
                }
            }
        }
        
        final int[][] right = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[i][j] = (j + 1 < n ? right[i][j + 1] + 1 : 1);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) continue;
                final int candidate = min(up[i][j], down[i][j], left[i][j], right[i][j]);
                result = Math.max(result, candidate);
            }
        }

        return result;
    }

    private int min(int up, int down, int left, int right) {
        return Math.min(Math.min(up, down), Math.min(left, right));
    }
}
