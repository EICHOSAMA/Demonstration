package per.eicho.demo.leetcode.q801_900;

/**
 * <p>829. Consecutive Numbers Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/consecutive-numbers-sum/">
 *   829. Consecutive Numbers Sum</a>
 */
public final class Q829 {
    public int consecutiveNumbersSum(int n) {
        return consecutiveNumbersSum((long)n);
    }

    private int consecutiveNumbersSum(long n) {
        // 1. 1 <= n <= 10^9
        int result = 0;

        for (long k = 2, bound = 2 * n; k * (k + 1) <= bound; k++) {
            // Condition1: 2n % k == 0
            if (bound % k != 0) continue;

            // Condition2: 2L = (2n / k) + 1 - k
            //    isEven((2n / k) + 1 - k);
            if (((bound / k) + 1 - k) % 2 != 0) continue;
            result++;
        }

        return result;
    }

}
