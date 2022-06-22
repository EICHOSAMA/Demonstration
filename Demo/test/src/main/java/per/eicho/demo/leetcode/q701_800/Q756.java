package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>756. Pyramid Transition Matrix 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/pyramid-transition-matrix/">
 *   756. Pyramid Transition Matrix</a>
 */
public final class Q756 {
    
    @SuppressWarnings("unchecked")
    final List<Integer>[][] allowInfo = (List<Integer>[][]) new List[26][26];
    int[][] pyramid;
    int n;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // 1. 2 <= bottom.length <= 6
        // 2. 0 <= allowed.length <= 216
        // 3. allowed[i].length == 3
        // 4. The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.
        // 5. All the values of allowed are unique.
        processAllowed(allowed);
        n = bottom.length();
        pyramid = new int[n][n];
        for (int i = 0; i < n; i++) pyramid[0][i] = bottom.charAt(i) - 'A';
        return pyramidTransition(1, 0, n - 1);
    }

    private boolean pyramidTransition(final int layer, int p, final int count) {
        if (layer == n) return true;
        if (p == count) {
            return pyramidTransition(layer + 1, 0, count - 1);
        }

        final int preLayer = layer - 1;
        final List<Integer> candidates = allowInfo[pyramid[preLayer][p]][pyramid[preLayer][p + 1]];
        if (candidates == null) return false;

        for (Integer candidate : candidates) {
            pyramid[layer][p] = candidate;
            if (pyramidTransition(layer, p + 1, count)) return true;
        }
        pyramid[layer][p] = 0;
        return false;
    }

    private void processAllowed(List<String> allowed) {
        // 2. 0 <= allowed.length <= 216
        // 3. allowed[i].length == 3        
        for (String allow : allowed) {
            final int i = allow.charAt(0) - 'A';
            final int j = allow.charAt(1) - 'A';
            final int k = allow.charAt(2) - 'A';
            if (allowInfo[i][j] == null) allowInfo[i][j] = new ArrayList<>();
            allowInfo[i][j].add(k);
        }
    }
}
