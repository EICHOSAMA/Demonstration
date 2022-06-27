package per.eicho.demo.leetcode.q801_900;

/**
 * <p>807. Max Increase to Keep City Skyline 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/max-increase-to-keep-city-skyline/">
 *  807. Max Increase to Keep City Skyline</a>
 */
public final class Q807 {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // 1. n == grid.length
        // 2. n == grid[r].length
        // 3. 2 <= n <= 50
        // 4. 0 <= grid[r][c] <= 100        
        final int n = grid.length;
        final int[] rowMax = new int[n];
        final int[] colMax = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int height = grid[i][j];
                rowMax[i] = Math.max(rowMax[i], height);
                colMax[j] = Math.max(colMax[j], height);
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            final int rMax = rowMax[i];
            for (int j = 0; j < n; j++) {
                final int cMax = colMax[j];
                final int height = grid[i][j];
                result += (Math.min(rMax, cMax) - height);
            }
        }

        return result;
    }
}
