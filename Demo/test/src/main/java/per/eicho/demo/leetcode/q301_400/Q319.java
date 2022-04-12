package per.eicho.demo.leetcode.q301_400;

/**
 * <p>319. Bulb Switcher 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/bulb-switcher/">
 *   319. Bulb Switcher</a>
 */
public class Q319 {
    public int bulbSwitch(int n) {
        // 1. 0 <= n <= 10^9
        if (n == 0) return 0;
        return binarySearch(n, 1, 46340);
    }
    
    private int binarySearch(final int target, final int l, final int r) {
        if (l == r) return l;
        
        final int mid = (l + r + 1) / 2;
        if (mid * mid > target) return binarySearch(target, l, mid - 1);
        return binarySearch(target, mid, r);
    }
}
