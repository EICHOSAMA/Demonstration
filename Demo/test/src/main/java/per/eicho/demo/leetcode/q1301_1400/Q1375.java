package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1375. Number of Times Binary String Is Prefix-Aligned 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned/">
 *   1375. Number of Times Binary String Is Prefix-Aligned</a>
 */
public final class Q1375 {
    public int numTimesAllBlue(int[] flips) {
        // 1. n == flips.length
        // 2. 1 <= n <= 5 * 10^4
        // 3. flips is a permutation of the integers in the range [1, n].
        final int n = flips.length;
        final int[] marks = new int[n];
        int sum = 0;
        int result = 0;
        for (int i = 0, count1 = 1; i < n; i++, count1++) {
            final int flip = flips[i] - 1; // 1-indexed -> 0-indexed.
            marks[flip] = 1;
            if (flip < i) sum++;

            // sum = getSum[0, i]
            sum += marks[i];

            if (sum == count1) result++;
        }
        return result;
    }
}
