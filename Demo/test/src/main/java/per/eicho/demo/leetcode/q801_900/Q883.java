package per.eicho.demo.leetcode.q801_900;

/**
 * <p>883. Projection Area of 3D Shapes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/projection-area-of-3d-shapes/">883. Projection Area of 3D Shapes</a>
 */
public final class Q883 {
    public int projectionArea(int[][] grid) {
        // 1. n == grid.length == grid[i].length
        // 2. 1 <= n <= 50
        // 3. 0 <= grid[i][j] <= 50        
        final int n = grid.length;
        int result = 0;
        
        // 1. x
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) result++;
            }
        }

        // 2. y
        for (int i = 0; i < n; i++) {
            int max = grid[i][0];

            for (int j = 1; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }

            result += max;
        }

        // 3. z
        for (int i = 0; i < n; i++) {
            int max = grid[0][i];

            for (int j = 1; j < n; j++) {
                max = Math.max(max, grid[j][i]);
            }

            result += max;
        }
        return result;
    }
}
