package per.eicho.demo.leetcode.q2201_2300;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * <p>2231. Largest Number After Digit Swaps by Parity 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/">
 *   2231. Largest Number After Digit Swaps by Parity</a>
 */
public final class Q2231 {
    public int largestInteger(int num) {
        // 1. 1 <= num <= 10^9  (1_000_000_000)
        final LinkedList<Integer> oddDigits = new LinkedList<>();
        final LinkedList<Integer> evenDigits = new LinkedList<>();
        final boolean[] marks = new boolean[10]; // true: even, false: odd
        int p = 0;
        while (num != 0) {
            final int digit = num % 10;
            num /= 10;
            if (digit % 2 == 0) {
                evenDigits.add(digit);
                marks[p++] = true;
            } else {
                oddDigits.add(digit);
                marks[p++] = false;
            } 
        }
        
        final Comparator<Integer> comparator = (num1, num2) -> num2.compareTo(num1);
        oddDigits.sort(comparator);
        evenDigits.sort(comparator);

        int result = 0;
        while (--p >= 0) {
            final boolean mark = marks[p];
            if (mark) {
                // even
                result = result * 10 + evenDigits.pollFirst();
            } else {
                // odd
                result = result * 10 + oddDigits.pollFirst();
            }
        }

        return result;
    }
}
