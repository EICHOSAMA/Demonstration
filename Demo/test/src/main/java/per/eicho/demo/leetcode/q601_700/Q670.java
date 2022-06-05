package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>670. Maximum Swap 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-swap/">
 *   670. Maximum Swap</a>
 */
public final class Q670 {
    public int maximumSwap(int num) {
        // 0 <= num <= 10^8
        int count = 1;
        int n = 1;
        while (num / n >= 10) {
            n *= 10;
            count++;
        }

        final int[] digits = new int[count];
        int tempNum = num;
        for (int i = 0; i < count; i++) {
            digits[i] = tempNum % 10;
            tempNum /= 10;
        }

        final int[] sortedDigits = Arrays.copyOf(digits, count);
        Arrays.sort(sortedDigits);

        int r = count - 1;
        while (r >= 0 && digits[r] == sortedDigits[r]) r--;
        if (r < 0) return num; // no need to swap
        final int digitToSwap = sortedDigits[r];
        int l = 0;
        while (digits[l] != digitToSwap) l++;

        // swap
        digits[l] = digits[r];
        digits[r] = digitToSwap;

        int newNum = 0;
        for (int i = count - 1; i >= 0; i--) newNum = newNum * 10 + digits[i];
        return newNum;
    }
}
