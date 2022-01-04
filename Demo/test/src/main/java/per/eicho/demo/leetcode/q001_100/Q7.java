package per.eicho.demo.leetcode.q001_100;

/**
 * <p>7. Reverse Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-integer/">7. Reverse Integer</a>
 */
public final class Q7 {
    public int reverse(int x) {
        if (x == 0) return 0;

        long result = 0L;
        int digit;
        while (x != 0) {
            digit = x % 10;
            x /= 10;
            result = result * 10L + (long)digit;
        }
        if (result < Integer.MIN_VALUE ||
            result > Integer.MAX_VALUE)
            return  0; // over flow

        return (int)result;
    }
}
