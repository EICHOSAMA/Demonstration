package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>684. Redundant Connection 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/redundant-connection/">684. Redundant Connection</a>
 */
public final class Q684 {
    public int[] findRedundantConnection(int[][] edges) {
        // 1. n == edges.length
        // 2. 3 <= n <= 1000
        // 3. edges[i].length == 2
        // 4. 1 <= ai < bi <= edges.length
        // 5. ai != bi
        // 6. There are no repeated edges.
        // 7. The given graph is connected.
        final int n = edges.length;
        final DisjointSetForest ds = new DisjointSetForest(n + 1);
        for (int[] edge : edges) {
            final int a = edge[0];
            final int b = edge[1];
            if (ds.find(a) == ds.find(b)) return edge;
            ds.union(a, b);
        }
        return null;
    }
}
