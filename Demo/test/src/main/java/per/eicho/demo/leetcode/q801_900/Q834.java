package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import per.eicho.demo.leetcode.debug.OutputUtils;

import java.util.Map;

/**
 * <p>834. Sum of Distances in Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-distances-in-tree/">
 *   834. Sum of Distances in Tree</a>
 */
public final class Q834 {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        // 1. 1 <= n <= 3 * 10^4
        // 2. edges.length == n - 1
        // 3. edges[i].length == 2
        // 4. 0 <= ai, bi < n
        // 5. ai != bi
        // 6. The given input represents a valid tree.
        if (n == 1) return new int[]{0};
        final int[] countEdges = new int[n];
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            countEdges[edge[0]]++;
            countEdges[edge[1]]++;
            
            addEdge(graph, edge[0], edge[1]);
            addEdge(graph, edge[1], edge[0]);
        }

        final Stack<List<Integer>> layers = new Stack<>();
        final List<Integer> firstLayer = new ArrayList<>();
        layers.add(firstLayer);
        int count = 0;
        final boolean[] marks = new boolean[n];        
        for (int i = 0; i < n; i++) {
            if (countEdges[i] != 1) continue;
            firstLayer.add(i);
            count++;
            marks[i] = true;
        }

        final int[] nodeCounts = new int[n];
        Arrays.fill(nodeCounts, 1);
        final int[] distances = new int[n];
        // [0, 1]

        while (count != n) {
            final List<Integer> prevLayer = layers.peek();
            final List<Integer> nextLayer = new ArrayList<>();
            
            for (int from : prevLayer) {
                final List<Integer> edgeList = graph.get(from);
                for (int to : edgeList) {
                    if (marks[to]) continue;
                    
                    nodeCounts[to] += nodeCounts[from];
                    distances[to] += distances[from] + nodeCounts[from];
                    if (--countEdges[to] == 1) {
                        nextLayer.add(to);
                        count++;
                    };
                }
            }
            for (int node : nextLayer) marks[node] = true;
            if (nextLayer.size() != 0) layers.add(nextLayer);
        }

        if (layers.peek().size() == 2) {
            final int from = layers.peek().get(0);
            final int to = layers.peek().get(1);

            final int dis1 = distances[from] + distances[to] + nodeCounts[to];
            final int dis2 = distances[to] + distances[from] + nodeCounts[from];
            distances[from] = dis1;
            distances[to] = dis2;

            nodeCounts[from] = n;
            nodeCounts[to] = n;
            count -= 2;
        } else {
            count -= 1;
        }

        Arrays.fill(marks, false);
        while (count > 0) {
            final List<Integer> layer = layers.pop();
            for (int node : layer) marks[node] = true;

            for (int from : layer) {
                final List<Integer> edgeList = graph.get(from);
                final int distance = distances[from];
                for (int to : edgeList) {
                    if (marks[to]) continue;
                    
                    distances[to] = distance + (n - nodeCounts[to] * 2);
                }
            }

            count -= layers.peek().size();
        }

        return distances;
    }

    private void addEdge(Map<Integer, List<Integer>> graph, int from, int to) {
        if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
        graph.get(from).add(to);
    }

    public static void main(String[] args) {
        Q834 q834 = new Q834();
        int[] output = q834.sumOfDistancesInTree(7, new int[][]{
            new int[]{0, 1}, new int[]{0, 2},
            new int[]{2, 3}, new int[]{2, 4},
            new int[]{2, 5} , new int[]{5, 6}
        });
        OutputUtils.println(output);
    }
}
