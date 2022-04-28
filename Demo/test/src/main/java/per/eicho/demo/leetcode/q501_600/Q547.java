package per.eicho.demo.leetcode.q501_600;

import java.util.HashSet;
import java.util.Set;

import per.eicho.demo.datastructure.tree.disjoint.DisjointSetForest;

/**
 * <p>547. Number of Provinces 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-provinces/">
 *   547. Number of Provinces</a>
 */
public final class Q547 {
    public int findCircleNum(int[][] isConnected) {
        // 1. 1 <= n <= 200
        // 2. n == isConnected.length
        // 3. n == isConnected[i].length
        // 4. isConnected[i][j] is 1 or 0.
        // 5. isConnected[i][i] == 1
        // 6. isConnected[i][j] == isConnected[j][i]
        final int n = isConnected.length;
        final DisjointSetForest ds = new DisjointSetForest(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) ds.union(i, j);
            }
        }

        final Set<Integer> provinces = new HashSet<>();

        for (int i = 0; i < n; i++) {
            final int pres = ds.find(i);
            if (!provinces.contains(pres)) provinces.add(pres);
        }

        return provinces.size();
    }
}
