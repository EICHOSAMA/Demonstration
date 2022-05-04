package per.eicho.demo.leetcode.q1501_1600;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>1584. Min Cost to Connect All Points 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/min-cost-to-connect-all-points/">
 *   1584. Min Cost to Connect All Points</a>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Prim%27s_algorithm">Prim's algorithm - wikipedia</a>
 */
public final class Q1584 {

    private static class Edge implements Comparable<Edge> {

        final int from;
        final int to;
        final int distance;

        Edge(int[][] points, int from, int to) {
            this.from = from;
            this.to = to;

            final int[] p1 = points[from];
            final int[] p2 = points[to]; 
            distance = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(distance, o.distance);
        }
    }

    public int minCostConnectPoints(int[][] points) {
        // 1. 1 <= points.length <= 1000
        // 2. -10^6 <= xi, yi <= 10^6
        // 3. All pairs (xi, yi) are distinct.
        final int n = points.length;
        final List<Edge> edges = new ArrayList<>(n * (n - 1) / 2); // (n - 1) + (n - 2) + ... + 1 = (n) * (n - 1) / 2 
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(points, i, j));
            }
        }
        edges.sort(null);

        final DisjointSetForest ds = new DisjointSetForest(n); 
        int result = 0, edgeNum = 1; // if edgeNum = 0, the judge should be (edgeNum == n - 1), so add offset 1.
        for (Edge edge : edges) {
            if (ds.find(edge.from) == ds.find(edge.to)) continue;
            ds.union(edge.from, edge.to);
            edgeNum++;
            result += edge.distance;
            if (edgeNum == n) break;
        }

        return result;
    }
}
