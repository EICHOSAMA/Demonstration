package per.eicho.demo.leetcode.q1601_1700;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>1631. Path With Minimum Effort 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/min-cost-to-connect-all-points/">
 *   1631. Path With Minimum Effort</a>
 */
public final class Q1631 {

    private int[][] heights;

    public int minimumEffortPath(int[][] heights) {
        // 1. rows == heights.length
        // 2. columns == heights[i].length
        // 3. 1 <= rows, columns <= 100
        // 4. 1 <= heights[i][j] <= 10^6
        final int n = heights.length;
        final int m = heights[0].length;
        this.heights = heights;
        final DisjointSetForest ds = new DisjointSetForest(n * m);

        final List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                final int id = i * m + j;
                if (i + 1 < n) edges.add(new int[]{id, id + m, effort(i, j, i + 1, j)});
                if (j + 1 < m) edges.add(new int[]{id, id + 1, effort(i, j, i, j + 1)});
            }
        }
        edges.sort((e1, e2) -> Integer.compare(e1[2], e2[2]));

        final int from = 0;
        final int to = m * n - 1;

        for (int i = 0; i < edges.size(); i++) {
            final int[] edge = edges.get(i);
            
            ds.union(edge[0], edge[1]);
            
            if (ds.find(from) == ds.find(to)) return edge[2];
        }
        return 0;
    }

    private int effort(int x, int y, int nx, int ny) {
        return Math.abs(heights[x][y] - heights[nx][ny]);
    }
}
