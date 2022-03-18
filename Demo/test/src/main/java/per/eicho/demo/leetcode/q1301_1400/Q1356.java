package per.eicho.demo.leetcode.q1301_1400;

import java.util.Arrays;

/**
 * <p>1356. Sort Integers by The Number of 1 Bits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/">
 *   1356. Sort Integers by The Number of 1 Bits</a>
 */
public final class Q1356 {
    public int[] sortByBits(int[] arr) {
        // 1. 1 <= arr.length <= 500
        // 2. 0 <= arr[i] <= 10^4
        return Arrays.stream(arr)
            .boxed()
            .sorted((num1, num2) -> {
                final int c1 = count1Bit(num1);
                final int c2 = count1Bit(num2);
                if (c1 != c2) return Integer.compare(c1, c2);
                return Integer.compare(num1, num2);
            })
            .mapToInt(num -> num.intValue())
            .toArray();
    }

    private int count1Bit(int num) {
        int count = 0;
        while (num != 0) {
            num ^= (num & -num);
            count++;
        }
        return count;
    }
}
