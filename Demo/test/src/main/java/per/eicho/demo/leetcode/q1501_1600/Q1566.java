package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1566. Detect Pattern of Length M Repeated K or More Times 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/">
 *   1566. Detect Pattern of Length M Repeated K or More Times</a>
 */
public final class Q1566 {
    public boolean containsPattern(int[] arr, int m, int k) {
        // 1. 2 <= arr.length <= 100
        // 2. 1 <= arr[i] <= 100
        // 3. 1 <= m <= 100
        // 4. 2 <= k <= 100        
        // find a pattern of length m that is repeated k or more times.
        final int n = arr.length;

        outer:
        for (int l = 0, r = m * k - 1; r < n; l++, r++) {
            // [l, r]
            int p1 = l;
            for (int i = 1; i < k; i++) {
                int p2 = l + m * i;
                for (int j = 0; j < m; j++) {
                    if (arr[p1 + j] != arr[p2 + j]) continue outer;
                }
            }
            return true;
        }

        return false;
    }
}
