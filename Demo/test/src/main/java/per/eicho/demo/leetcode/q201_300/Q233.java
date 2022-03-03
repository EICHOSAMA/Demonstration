package per.eicho.demo.leetcode.q201_300;

/**
 * <p>233. Number of Digit One 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-digit-one/">
 *   233. Number of Digit One</a>
 */
public final class Q233 {

    private static int[] sum = new int[]{1, 20, 300, 4_000, 50_000, 600_000, 7_000_000, 80_000_000, 900_000_000};
    private static int[] f = new int[]{1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000};

    public int countDigitOne(int n) {
        // 1. 0 <= n <= 10^9
        if (n == 0) return 0;
        if (n < 10) return 1;

        int power = 0;

        long temp = 1;
        while (n / temp != 0) {
            temp *= 10;
            power++;
        }
        int num = (int)(temp / 10);
        power--;

        final int firstDigit = n / num;

        if (firstDigit == 1) {
            return ((n % num) + 1) + sum[power - 1] + countDigitOne(n % num);
        }

        return f[power] + firstDigit * sum[power - 1] + countDigitOne(n % num);
    }

    public static void main(String[] args) {
        Q233 q233 = new Q233();
        System.out.println(q233.countDigitOne(1_000_000_000));  
    }
}
