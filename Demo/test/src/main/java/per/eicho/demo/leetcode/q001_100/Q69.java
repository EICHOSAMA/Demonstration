package per.eicho.demo.leetcode.q001_100;

/**
 * <p>69. Sqrt(x) 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sqrtx/">69. Sqrt(x)</a>
 */
public final class Q69 {
    public int mySqrt(int x) {
        // 1. 0 <= x <= 2^31 - 1
        if (x < 0) return 0;
        return binartSearch(x, 0, x);
    }


    private int binartSearch(final int target, final int l, final int r) {
        if (r == l) return l;

        int mid = (int)(((long)l + (long)r + 1L) / 2L);

        if ((target / mid) / mid == 0)
            return binartSearch(target, l, mid - 1);
        else
            return binartSearch(target, mid, r);
    }
}
