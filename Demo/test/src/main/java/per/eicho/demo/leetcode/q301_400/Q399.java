package per.eicho.demo.leetcode.q301_400;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>399. Evaluate Division 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/evaluate-division/">399. Evaluate Division</a>
 */
public final class Q399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 1. 1 <= equations.length <= 20
        // 2. equations[i].length == 2
        // 3. 1 <= Ai.length, Bi.length <= 5
        // 4. values.length == equations.length
        // 5. 0.0 < values[i] <= 20.0
        // 6. 1 <= queries.length <= 20
        // 7. queries[i].length == 2
        // 8. 1 <= Cj.length, Dj.length <= 5
        // 9. Ai, Bi, Cj, Dj consist of lower case English letters and digits.
        final Map<String, Integer> idMap = genIDMap(equations);
        final double[][] graph = genGraph(equations, idMap, values);
        final double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            final List<String> queue = queries.get(i);

            if (!idMap.containsKey(queue.get(0)) || !idMap.containsKey(queue.get(1))) {
                result[i] = -1.0d;
                continue;
            }

            final int from = idMap.get(queue.get(0));
            final int to = idMap.get(queue.get(1));
            result[i] = search(graph, from, to);
        }

        return result;
    }

    private Map<String, Integer> genIDMap(List<List<String>> equations) {
        Map<String, Integer> idMap = new HashMap<>();

        int id = 0;
        for (List<String> equation : equations) {
            for (String key : equation) {
                if (idMap.containsKey(key)) continue;
                idMap.put(key, id++);
            }
        }
        return idMap;
    }

    private double[][] genGraph(List<List<String>> equations, Map<String, Integer> idMap, double[] values) {
        final int n = idMap.size();

        final double[][] graph = new double[n][n];

        for (int i = 0; i < n; i++) graph[i][i] = 1.0d;

        final int cnt = equations.size();
        for (int i = 0; i < cnt; i++) {
            final List<String> equation = equations.get(i);
            final double value = values[i];

            final int from = idMap.get(equation.get(0));
            final int to = idMap.get(equation.get(1));
            // from / to = value

            graph[from][to] = value;
            graph[to][from] = 1 / value;
        }    
        return graph;
    }

    private double search(double[][] graph, int from, int to) {
        if (graph[from][to] != 0.0d) return graph[from][to];

        final Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (graph[from][i] > 0.0d && i != from) queue.add(i); 
        }
        
        while (!queue.isEmpty()) {
            Integer iID = queue.poll();
            int id = iID;
            double val = graph[from][id];

            for (int i = 0; i < graph.length; i++) {
                if (graph[from][i] != 0.0d) continue; // already searched.

                final double edge = graph[id][i];
                if (edge <= 0.0d) continue;
                graph[from][i] = val * edge;
                queue.add(i);
            }
        }

        if (graph[from][to] == 0.0d) graph[from][to] = -1.0d;
        return graph[from][to];
    }
}
