package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <p>886. Possible Bipartition 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/possible-bipartition/">
 *   886. Possible Bipartition</a>
 */
public final class Q886 {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 1. 1 <= n <= 2000
        // 2. 0 <= dislikes.length <= 10^4
        // 3. dislikes[i].length == 2
        // 4. 1 <= dislikes[i][j] <= n
        // 5. ai < bi
        // 6. All the pairs of dislikes are unique.
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            final Integer from = dislike[0];
            final Integer to = dislike[1];

            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            final List<Integer> edges = graph.get(from);
            edges.add(to);

            if (!graph.containsKey(to)) graph.put(to, new ArrayList<>());
            final List<Integer> edges2 = graph.get(to);
            edges2.add(from);
        }


        final int[] group = new int[n + 1]; // [1, n]
        final Queue<Integer> processingNodes = new LinkedList<>();
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            final Integer from = entry.getKey();

            if (group[from] != 0) continue;
            group[from] = 1;
            processingNodes.add(from);

            while (!processingNodes.isEmpty()) {
                final Integer node = processingNodes.poll();
                final int color = group[node];
                final int nColor = color * -1;

                final List<Integer> edges = graph.get(node);

                for (Integer to : edges) {
                    if (group[to] == color) return false;
                    if (group[to] == 0) {
                        group[to] = nColor;
                        processingNodes.add(to);
                    }
                }
            }
        }
        
        return true;
    }
}
