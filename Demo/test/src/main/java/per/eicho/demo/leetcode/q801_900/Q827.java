package per.eicho.demo.leetcode.q801_900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>827. Making A Large Island 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/making-a-large-island/">
 *   827. Making A Large Island</a>
 */
public final class Q827 {

    int n;

    private static final int[][] directions = new int[][]{
        new int[]{1, 0}, new int[]{0, 1},
        new int[]{-1, 0}, new int[]{0, -1}
    };

    public int largestIsland(int[][] grid) {
        // 1. n == grid.length
        // 2. n == grid[i].length
        // 3. 1 <= n <= 500
        // 4. grid[i][j] is either 0 or 1.
        n = grid.length;
        int id = 2;
        // Map<IsLand ID, Area>
        final Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                final int area = dfs(grid, i, j, id);
                map.put(id++, area);
                result = Math.max(result, area);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;
                set.clear();

                for (int[] direction : directions) {
                    final int nx = i + direction[0];
                    if (nx < 0 || nx >= n) continue;
                    final int ny = j + direction[1];
                    if (ny < 0 || ny >= n) continue;
                    if (grid[nx][ny] < 2) continue;
                    set.add(grid[nx][ny]);
                }

                int candidate = 1;
                for (Integer islandID : set) candidate += map.get(islandID);
                result = Math.max(result, candidate);
            }
        }

        return result;
    }

    private int dfs(int[][] grid, int x, int y, int id) {
        grid[x][y] = id;

        int area = 1;
        for (int[] direction : directions) {
            final int nx = x + direction[0];
            if (nx < 0 || nx >= n) continue;
            final int ny = y + direction[1];
            if (ny < 0 || ny >= n) continue;
            if (grid[nx][ny] != 1) continue;
            area += dfs(grid, nx, ny, id);
        }
        
        return area;
    }
}
