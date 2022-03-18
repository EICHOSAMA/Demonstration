package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1317. Convert Integer to the Sum of Two No-Zero Integers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/">
 *   1317. Convert Integer to the Sum of Two No-Zero Integers</a>
 */
public final class Q1317 {
    public int[] getNoZeroIntegers(int n) {
        // 1. 2 <= n <= 10^4
        for (int a = 1; a <= n - 1; a++) {
            final int b = n - a;
            if (isNoZeroInteger(a) && isNoZeroInteger(b)) return new int[]{a, b};
        }
        return null;
    }

    private boolean isNoZeroInteger(int num) {
        while (num != 0) {
            final int digit = num % 10;
            num /= 10;
            if (digit == 0) return false;
        }
        return true;
    }
}
