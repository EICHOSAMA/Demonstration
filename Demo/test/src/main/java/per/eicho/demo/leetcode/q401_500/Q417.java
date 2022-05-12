package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>417. Pacific Atlantic Water Flow 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/pacific-atlantic-water-flow/">
 *   417. Pacific Atlantic Water Flow</a>
 */
public final class Q417 {
    static int[][] movements = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) dfs(i, 0, pacific);
        for (int j = 1; j < n; j++) dfs(0, j, pacific);
        for (int i = 0; i < m; i++) dfs(i, n - 1, atlantic);
        for (int j = 0; j < n - 1; j++) dfs(m - 1, j, atlantic);
        
        final List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) result.add(Arrays.asList(i, j));
            }
        }
        return result;
    }

    public void dfs(int i, int j, boolean[][] ocean) {
        if (ocean[i][j]) return;

        ocean[i][j] = true;
        for (int[] move : movements) {
            final int ni = i + move[0];
            final int nj = j + move[1];
            if (isValid(ni, nj) && heights[ni][nj] >= heights[i][j]) dfs(ni, nj, ocean);
        }
    }

    private boolean isValid(int ni, int nj) {
        return 0 <= ni && ni < m && 0 <= nj && nj < n;
    }
}
