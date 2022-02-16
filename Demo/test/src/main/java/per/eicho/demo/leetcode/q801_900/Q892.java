package per.eicho.demo.leetcode.q801_900;

/**
 * <p>884. Uncommon Words from Two Sentences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/uncommon-words-from-two-sentences/">884. Uncommon Words from Two Sentences</a>
 */
public final class Q892 {
    public int surfaceArea(int[][] grid) {
        // 1. n == grid.length == grid[i].length
        // 2. 1 <= n <= 50
        // 3. 0 <= grid[i][j] <= 50        
        final int n = grid.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            
            for (int j = 0; j < n; j++) {
                final int num = grid[i][j];

                result = result + num * 6;
                if (num >= 2) result -= (num - 1) * 2;
                
                if (j + 1 < n) result -= Math.min(num, grid[i][j + 1]) * 2;
                if (i + 1 < n) result -= Math.min(num, grid[i + 1][j]) * 2;
            }
        }
        return result;
    }
}
