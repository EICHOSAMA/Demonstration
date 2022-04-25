package per.eicho.demo.leetcode.q2101_2200;

import java.util.Arrays;

/**
 * <p>2160. Minimum Sum of Four Digit Number After Splitting Digits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/">
 *   2160. Minimum Sum of Four Digit Number After Splitting Digits</a>
 */
public final class Q2160 {
    public int minimumSum(int num) {
        // 1. 1000 <= num <= 9999
        final int[] digits = new int[4];
        int p = 0;
        while (p < 4) {
            digits[p++] = num % 10;
            num /= 10;
        }
        Arrays.sort(digits);
        return (digits[0] + digits[1]) * 10 + digits[2] + digits[3];
    }
}
