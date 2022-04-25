package per.eicho.demo.leetcode.q2101_2200;

import java.util.LinkedList;

/**
 * <p>2164. Sort Even and Odd Indices Independently 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-even-and-odd-indices-independently/">
 *   2164. Sort Even and Odd Indices Independently</a>
 */
public final class Q2164 {
    public int[] sortEvenOdd(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. 1 <= nums[i] <= 100
        final LinkedList<Integer> oddDigits = new LinkedList<>();
        final LinkedList<Integer> evenDigits = new LinkedList<>();
        final int n = nums.length;
        int p = 0;
        while (p < n) {
            final int digit = nums[p];
            if (p % 2 == 0) {
                evenDigits.add(digit);
            } else {
                oddDigits.add(digit);
            } 
            p++;
        }
        
        oddDigits.sort((num1, num2) -> num2.compareTo(num1)); // non-increasing
        evenDigits.sort(null);   // non-decreasing

        p = 0;
        while (p < n) {
            nums[p++] = evenDigits.pollFirst();
            if (p < n) nums[p++] = oddDigits.pollFirst();
        }

        return nums;
    }
}
