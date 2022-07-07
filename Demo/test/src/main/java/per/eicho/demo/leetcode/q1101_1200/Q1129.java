package per.eicho.demo.leetcode.q1101_1200;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>1129. Shortest Path with Alternating Colors 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/shortest-path-with-alternating-colors/">
 *   1129. Shortest Path with Alternating Colors</a>
 */
public final class Q1129 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // 1. 1 <= n <= 100
        // 2. 0 <= redEdges.length, blueEdges.length <= 400
        // 3. redEdges[i].length == blueEdges[j].length == 2
        // 4. 0 <= ai, bi, uj, vj < n
        final int[][] edges = new int[n][n]; 
        // 0b00 None, 0b01 Red, 0b10 Blud, 0b11 Red & Blue
        for (int[] edge : redEdges) {
            final int from = edge[0];
            final int to = edge[1];

            edges[from][to] |= 0b01; // red
        }

        for (int[] edge : blueEdges) {
            final int from = edge[0];
            final int to = edge[1];

            edges[from][to] |= 0b10; // red
        }
        

        final int[][] f = new int[n][2];
        for (int[] row : f) Arrays.fill(row, Integer.MAX_VALUE);
        Arrays.fill(f[0], 0);

        final Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(0);
        bfsQueue.add(1);

        while (!bfsQueue.isEmpty()) {
            final int idx = bfsQueue.poll();
            final int node = idx / 2;
            final int colorIdx = (idx % 2); // [0, 1]
            final int len = f[node][colorIdx];
            final int targetColorCode = (colorIdx ^ 1) + 1; // 0b01 | 0b10
            final int targetColorIdx = targetColorCode - 1;

            for (int i = 0; i < n; i++) {
                final int edgeInfo = edges[node][i];
                if (edgeInfo == 0) continue;

                if ((edgeInfo & targetColorCode) == targetColorCode) {
                    // targetColor
                    if (f[i][targetColorIdx] != Integer.MAX_VALUE) continue;
                    f[i][targetColorIdx] = len + 1;
                    bfsQueue.add(i * 2 + targetColorIdx);
                }
            }
        }


        final int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = Math.min(f[i][0], f[i][1]);
            if (result[i] == Integer.MAX_VALUE) result[i] = -1;
        }
        return result;
    }
}
