package per.eicho.demo.leetcode.q1201_1300;

import java.util.HashSet;
import java.util.Set;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>1254. Number of Closed Islands 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-closed-islands/">
 *   1254. Number of Closed Islands</a>
 */
public final class Q1254 {

    private static final int[][] directions = new int[][] {
        new int[]{0, -1}, new int[]{-1, 0}
    };

    public int closedIsland(int[][] grid) {
        // 1. 1 <= grid.length, grid[0].length <= 100
        // 2. 0 <= grid[i][j] <= 1
        final int m = grid.length;
        final int n = grid[0].length;
        final DisjointSetForest dsf = new DisjointSetForest(m * n);
        
        for (int i = 0; i < m; i++) {
            final int[] row = grid[i];
            for (int j = 0; j < n; j++) {
                final int val = row[j];
                if (val == 1) continue;
                int ni, nj;
                for (int[] direction : directions) {
                    ni = i + direction[0];
                    if (ni < 0) continue;
                    nj = j + direction[1];
                    if (nj < 0) continue;

                    if (grid[ni][nj] == 1) continue;
                    final int idx = ni * n + nj;
                    dsf.union(i * n + j, idx);
                }
            }
        }

        final Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;
                final int idx = i * n + j;
                if (set.contains(dsf.find(idx))) continue;
                set.add(dsf.find(idx));
            }
        }

        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 0) {
                final int idx = 0 * n + i;
                if (set.contains(dsf.find(idx))) set.remove(dsf.find(idx));
            }

            if (grid[m - 1][i] == 0) {
                final int idx = (m - 1) * n + i;
                if (set.contains(dsf.find(idx))) set.remove(dsf.find(idx));
            }
        }

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                final int idx = i * n + 0;
                if (set.contains(dsf.find(idx))) set.remove(dsf.find(idx));
            }

            if (grid[i][n - 1] == 0) {
                final int idx = i * n + n - 1;
                if (set.contains(dsf.find(idx))) set.remove(dsf.find(idx));
            }            
        }

        return set.size();
    }
}
