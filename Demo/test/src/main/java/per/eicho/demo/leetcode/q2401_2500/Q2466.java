package per.eicho.demo.leetcode.q2401_2500;

/**
 * <p>2466. Count Ways To Build Good Strings 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-ways-to-build-good-strings/description/">2466. Count Ways To Build Good Strings</a>
 */
public final class Q2466 {
    private static final long MODULO = 1_000_000_000 + 7; //10^9 + 7.

    public int countGoodStrings(int low, int high, int zero, int one) {
        // 1. 1 <= low <= high <= 10^5
        // 2. 1 <= zero, one <= low, 
        // 3. target len [low, high]
        final long[] f = new long[high + 1];
        f[0] = 1L;
        long result = 0L;

        for (int i = 0; i <= high; i++) {
            final long count = f[i];

            if (i + zero <= high) f[i + zero] = (f[i + zero] + count) % MODULO;
            if (i + one <= high) f[i + one] = (f[i + one] + count) % MODULO; 
            
            if (low <= i) result += count;
        }

        return (int)(result % MODULO);
    }
}
