package per.eicho.demo.leetcode.q601_700;

/**
 * <p>668. Kth Smallest Number in Multiplication Table 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/">668. Kth Smallest Number in Multiplication Table</a>
 */
public final class Q668 {

    private int count(int mid, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) count += Math.min(n, mid / i);
        return count;
    }

    public int findKthNumber(int m, int n, int k) {
        // 1 <= m, n <= 3 * 10^4
        // 1 <= k <= m * n     
        int l = 1, r = m * n;
        while (l < r) {
            final int mid = (l + r) / 2;
            if (count(mid, m, n, k) < k) {
                // not enough.
                l = mid + 1;
            } else {
                // enough
                r = mid;
            }
        }
        return l;
    }
}
