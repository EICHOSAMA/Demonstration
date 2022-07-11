package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>797. All Paths From Source to Target 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/all-paths-from-source-to-target/">
 *   797. All Paths From Source to Target</a>
 */
public final class Q797 {
    List<List<Integer>> result;
    int target;
    int n;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 1. n == graph.length
        // 2. 2 <= n <= 15
        // 3. 0 <= graph[i][j] < n
        // 4. graph[i][j] != i (i.e., there will be no self-loops).
        // 5. All the elements of graph[i] are unique.
        // 6. The input graph is guaranteed to be a DAG.        
        result = new LinkedList<>();
        n = graph.length;
        target = n - 1;
        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        dfs(graph, 0, path);
        return result;
    }

    private void dfs(int[][] graph, int node, LinkedList<Integer> path) {
        if (node == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        final int[] neighbors = graph[node];

        for (int neighbor : neighbors) {
            path.add(neighbor);
            dfs(graph, neighbor, path);
            path.pollLast();
        }
    }
}
