package per.eicho.demo.leetcode.q101_200;

import java.util.Arrays;

/**
 * <p>174. Dungeon Game 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/dungeon-game/">174. Dungeon Game</a>
 */
public final class Q174 {
    public int calculateMinimumHP(int[][] dungeon) {
        // 1. m == dungeon.length
        // 2. n == dungeon[i].length
        // 3. 1 <= m, n <= 200
        // 4. -1000 <= dungeon[i][j] <= 1000        
        final int m = dungeon.length;
        final int n = dungeon[0].length;

        final int[][] f = new int[m + 1][n + 1];
        for (int[] row : f) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        f[m][n - 1] = 1;
        f[m - 1][n] = 1; 

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                final int min = Math.min(f[i + 1][j], f[i][j + 1]);
                f[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }

        return f[0][0];
    }

}
