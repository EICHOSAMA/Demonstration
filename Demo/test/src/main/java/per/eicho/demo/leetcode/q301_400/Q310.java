package per.eicho.demo.leetcode.q301_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>310. Minimum Height Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-height-trees/">
 *   310. Minimum Height Trees</a>
 */
public final class Q310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 1. 1 <= n <= 2 * 10^4
        // 2. edges.length == n - 1
        // 3. 0 <= ai, bi < n
        // 4. ai != bi
        // 5. All the pairs (ai, bi) are distinct.
        // 6. The given input is guaranteed to be a tree and there will be no repeated edges.
        if (n == 1) return Arrays.asList(0); 
        final Map<Integer, List<Integer>> graph = getGraphInstance(n);
        final int[] degrees = new int[n];
        initGraphAndDegrees(edges, graph, degrees);

        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) queue.add(i); 
        }
        
        int remain = n;
        while (!queue.isEmpty()) {
            final int currentLayerSize = queue.size();
            remain -= currentLayerSize;
            if (remain == 0) break;
            for (int i = 0; i < currentLayerSize; i++) {
                final Integer p = queue.poll();
                final List<Integer> neighbors = graph.get(p);

                for (int neighbor : neighbors) {
                    degrees[neighbor]--; // 入度-1
                    if (degrees[neighbor] == 1) queue.add(neighbor);
                }
            }
        }
        return (LinkedList<Integer>)queue;
    }

    private void initGraphAndDegrees(int[][] edges, final Map<Integer, List<Integer>> graph, final int[] degrees) {
        for (int[] edge : edges) {
            final Integer a = edge[0];
            final Integer b = edge[1];

            graph.get(a).add(b);
            graph.get(b).add(a);

            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
    }

    private Map<Integer, List<Integer>> getGraphInstance(int n) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        return graph;
    }
}
