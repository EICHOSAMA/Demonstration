package per.eicho.demo.leetcode.q601_700;

/**
 * <p>633. Sum of Square Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-square-numbers/">
 *   633. Sum of Square Numbers</a>
 */
public final class Q633 {
    public boolean judgeSquareSum(int c) {
        // 0 <= c <= 2^31 - 1
        for (int a = 0; a < 46340; a++) {
            final int ap2 = a * a;
            if (ap2 > c) break;
            final int bp2 = c - ap2;
            if (canSqrt(bp2)) return true;
        }
        
        return false;
    }

    private boolean canSqrt(int num) {
        int l = 0, r = 46340;
        
        while (l != r) {
            final int mid = (l + r + 1) / 2;
            final int midP2 = mid * mid;
            if (midP2 == num) return true;
            if (midP2 < num) {
                l = mid;
            } else { // midP2 > num
                r = mid - 1;
            }
        }
        return l * l == num;
    }
}
