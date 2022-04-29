package per.eicho.demo.leetcode.q701_800;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>785. Is Graph Bipartite? 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-provinces/">
 *   785. Is Graph Bipartite?</a>
 */
public final class Q785 {
    public boolean isBipartite(int[][] graph) {
        // 1. graph.length == n
        // 2. 1 <= n <= 100
        // 3. 0 <= graph[u].length < n
        // 4. 0 <= graph[u][i] <= n - 1
        // 5. graph[u] does not contain u.
        // 6. All the values of graph[u] are unique.
        // 7. If graph[u] contains v, then graph[v] contains u.        
        final int n = graph.length;
        final int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) continue;
            if (!wfs(graph, i, colors)) return false;    
        }
        return true;
    }

    private boolean wfs(int[][] graph, int p, int[] colors) {
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(p);
        colors[p] = 1;

        while (!queue.isEmpty()) {
            p = queue.poll();
            final int color = colors[p];
            final int adjacentColor = color * -1;

            final int[] edges = graph[p];
            for (int to : edges) {
                if (colors[to] == color) return false; // unexpected color.
                if (colors[to] == adjacentColor) continue;
                colors[to] = adjacentColor;
                queue.add(to);
            }
        }
        return true;
    }
}
