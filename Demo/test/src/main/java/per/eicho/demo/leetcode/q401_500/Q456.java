package per.eicho.demo.leetcode.q401_500;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * <p>456. 132 Pattern 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/132-pattern/">
 *   456. 132 Pattern</a>
 */
public final class Q456 {
    public boolean find132pattern(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 2 * 10^5
        // 3. -10^9 <= nums[i] <= 10^9
        final int n = nums.length;
        final Stack<Integer> monoStack = new Stack<>();
        monoStack.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; --i) {
            final int numI = nums[i]; // 1(i) 3(j) 2(k)
            if (numI < maxK) return true;
            while (!monoStack.isEmpty() && numI > monoStack.peek()) maxK = monoStack.pop();
            if (numI > maxK) monoStack.push(numI);
        }
        return false;
    }
}
