package per.eicho.demo.leetcode.q001_100;

/**
 * <p>28. Implement strStr() 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/implement-strstr/">28. Implement strStr()</a>
 */
public final class Q29 {
    public int divide(int dividend, int divisor) {
        // 1. -2^31 <= dividend, divisor <= 23^1 - 1
        // 2. divisor != 0        
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean isPositive = (dividend < 0 && divisor < 0) ||
                             (dividend > 0 && divisor > 0);
        if (isPositive)
            return (int)_divide(Math.abs((long)dividend), Math.abs((long)divisor));
        else
            return (int)(0L - _divide(Math.abs((long)dividend), Math.abs((long)divisor)));
    }

    private long _divide(long dividend, long divisor) {
        if (dividend < divisor)
            return 0;
        long offset = 1;
        long newDivisor = divisor;
        while (dividend - newDivisor >= newDivisor) {
            newDivisor = newDivisor << 1;
            offset = offset << 1;
        }
        return offset + _divide(dividend - newDivisor, divisor);
    }
}
