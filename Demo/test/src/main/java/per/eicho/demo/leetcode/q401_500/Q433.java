package per.eicho.demo.leetcode.q401_500;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * <p>433. Minimum Genetic Mutation 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-genetic-mutation/">
 *   433. Minimum Genetic Mutation</a>
 */
public final class Q433 {
    public int minMutation(String start, String end, String[] bank) {
        // 1. start.length == 8
        // 2. end.length == 8
        // 3. 0 <= bank.length <= 10
        // 4. bank[i].length == 8
        // 5. start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].        
        final Map<String, Integer> gene2Id = genGene2IdMap(start, bank);
        if (!gene2Id.containsKey(end)) return -1;

        final int n = gene2Id.size();
        final String[] genes = new String[n];
        for (String gene : gene2Id.keySet()) genes[gene2Id.get(gene)] = gene;
        final boolean[][] graph = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            final String from = genes[i];

            for (int j = i + 1; j < n; j++) {
                final String to = genes[j];
                if (canMove(from, to)) {
                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }

        // bfs
        final int target = gene2Id.get(end); 
        final Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        final boolean[] mark = new boolean[n];
        mark[0] = true;

        int time = 0;
        while (!queue.isEmpty()) {
            final int size = queue.size();

            for (int i = 0; i < size; i++) {
                final int from = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (!graph[from][j]) continue;
                    if (mark[j]) continue;

                    queue.add(j);
                    mark[j] = true;
                }
            }

            time++;
            if (mark[target]) return time;
        }

        return -1;
    }

    private Map<String, Integer> genGene2IdMap(String start, String[] bank) {
        int id = 0;
        Map<String, Integer> gene2Id = new HashMap<>();
        gene2Id.put(start, id++);
        for (String gene : bank) {
            if (!gene2Id.containsKey(gene)) gene2Id.put(gene, id++);
        }
        return gene2Id;
    }

    private boolean canMove(String from, String to) {
        // len == 8
        int diff = 0;
        for (int i = 0; i < 8; i++) if (from.charAt(i) != to.charAt(i)) diff++;
        return diff == 1;
    }
}
