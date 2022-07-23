package per.eicho.demo.leetcode.q801_900;

/**
 * <p>878. Nth Magical Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/nth-magical-number/">
 *  878. Nth Magical Number</a>
 */
public final class Q878 {

    private static final int MODULO = 1_000_000_000 + 7;

    public int nthMagicalNumber(int n, int a, int b) {
        // 1. 1 <= n <= 10^9
        // 2. 2 <= a, b <= 4 * 10^4
        final int gcd = gcd(a, b);
        final int lcm = (a * b) / gcd;

        final int count = (lcm / a) + (lcm / b) - 1;
        // per ${lcm} number has ${count} magical number.

        // n = count * x + y;
        // result = x * lcm + y-th (magical number, 0<= y < count) 
        final int x = n / count;
        final int y = n % count;
        
        int multipleOfA = a;
        int multipleOfB = b;
        int p = 0;
        int ythNum = 0;
        while (p < y) {
            p++;
            if (multipleOfA < multipleOfB) {
                ythNum = multipleOfA;
                multipleOfA += a;
            } else {
                ythNum = multipleOfB;
                multipleOfB += b;
            }
        }

        // result = x * lcm + y-th (magical number, 0<= y < count) 
        return (int)(((long)x * (long)(lcm % MODULO) + (long)ythNum) % MODULO);
    }

    private int gcd(int a, int b) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
