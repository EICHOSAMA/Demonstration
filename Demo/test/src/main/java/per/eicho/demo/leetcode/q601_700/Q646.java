package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>646. Maximum Length of Pair Chain 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-length-of-pair-chain/">
 *   646. Maximum Length of Pair Chain</a>
 */
public final class Q646 {
    public int findLongestChain(int[][] pairs) {
        // 1. n == pairs.length
        // 2. 1 <= n <= 1000
        // 3. -1000 <= lefti < righti <= 1000        
        final int n = pairs.length;
        Arrays.sort(pairs, (p1, p2) -> {
            if (p1[0] != p2[0]) return Integer.compare(p1[0], p2[0]);
            return Integer.compare(p1[0], p2[0]);
        });

        int maxLen = 1;
        final int[] f = new int[n];
        f[n - 1] = 1;
        for (int i = 0; i < n; i++) {
            if (f[i] != 0) continue;
            f[i] = search(f, i, pairs);
            maxLen = Math.max(maxLen, f[i]);
        }
        return maxLen;
    }

    private int search(int[] f, int p, int[][] pairs) {
        if (f[p] != 0) return f[p];
        
        int result = 0;
        for (int i = p + 1; i < f.length; i++) {
            if (pairs[p][1] < pairs[i][0]) {
                result = Math.max(result, search(f, i, pairs));
            }
        }
        return f[p] = result + 1;
    }
}
