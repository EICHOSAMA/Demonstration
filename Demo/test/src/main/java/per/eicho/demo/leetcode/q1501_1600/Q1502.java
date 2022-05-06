package per.eicho.demo.leetcode.q1501_1600;

import java.util.Arrays;

/**
 * <p>1502. Can Make Arithmetic Progression From Sequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/">
 *   1502. Can Make Arithmetic Progression From Sequence</a>
 */
public final class Q1502 {
    public boolean canMakeArithmeticProgression(int[] arr) {
        // 1. 2 <= arr.length <= 1000
        // 2. -10^6 <= arr[i] <= 10^6
        Arrays.sort(arr);
        final int diff = arr[1] - arr[0];
        final int n = arr.length;
        for (int i = 2; i < n; i++) {
            if (arr[i] - arr[i - 1] != diff) return false;
        }

        return true;
    }
}
