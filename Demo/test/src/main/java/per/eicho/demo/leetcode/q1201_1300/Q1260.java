package per.eicho.demo.leetcode.q1201_1300;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>1260. Shift 2D Grid 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shift-2d-grid/">
 *   1260. Shift 2D Grid</a>
 */
public final class Q1260 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // 1. m == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= m <= 50
        // 4. 1 <= n <= 50
        // 5. -1000 <= grid[i][j] <= 1000
        // 6. 0 <= k <= 100
        final int m = grid.length;
        final int n = grid[0].length;
        final int total = m * n;
        if (k >= total) k = k % total;
        int startIndex = m * n - k;
        final int[] cursor = new int[]{(startIndex / n) % m, startIndex % n}; // [0, m * n) row * n + col
        
        final List<List<Integer>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            final List<Integer> row = new ArrayList<>(n);
            result.add(row);

            for (int j = 0; j < n; j++) {
                row.add(grid[cursor[0]][cursor[1]]);
                moveToNext(cursor, m, n);
            }
        }
        return result;
    }

    private void moveToNext(int[] cursor, int m, int n) {
        cursor[1]++;
        if (cursor[1] == n) {
            cursor[1] = 0;
            cursor[0]++;
        }

        if (cursor[0] == m) {
            cursor[0] = 0;
        }
    }
}
