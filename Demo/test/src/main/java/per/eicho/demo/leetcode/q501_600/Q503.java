package per.eicho.demo.leetcode.q501_600;

import java.util.Deque;
import java.util.LinkedList;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>503. Next Greater Element II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/next-greater-element-ii/">
 *   503. Next Greater Element II</a>
 */
public final class Q503 {
    public int[] nextGreaterElements(int[] nums) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^9 <= nums[i] <= 10^9
        final int n = nums.length;
        final Deque<Integer> monoStack = new LinkedList<>(); // store idx info. decreasing
        final int[] result = new int[n];

        monoStack.add(0);
        int p = 1;
        while (p < n) {
            final int element = nums[p];

            while (!monoStack.isEmpty() && nums[monoStack.peekLast()] < element) {
                final int idx = monoStack.pollLast();
                result[idx] = element;
            }
            monoStack.add(p++);
        }

        final int idxOfFirstMaxElement = monoStack.peekFirst();
        p = 0;
        while (p <= idxOfFirstMaxElement) {
            final int element = nums[p];
            while (nums[monoStack.peekLast()] < element) {
                final int idx = monoStack.pollLast();
                result[idx] = element;
            }
            p++;
        }

        while (!monoStack.isEmpty()) result[monoStack.pop()] = -1;
        return result;
    }

    public static void main(String[] args) {
        Q503 q503 = new Q503();
        OutputUtils.println(q503.nextGreaterElements(new int[]{1,2,3,4,3}));
    }
}
