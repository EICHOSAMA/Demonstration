package per.eicho.demo.leetcode.q901_1000;

import java.util.Stack;

/**
 * <p>962. Maximum Width Ramp 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-width-ramp/">
 *   962. Maximum Width Ramp</a>
 */
public final class Q962 {
    public int maxWidthRamp(int[] nums) {
        // 1. 2 <= nums.length <= 5 * 10^4
        // 2. 0 <= nums[i] <= 5 * 10^4
        final Stack<Integer> monoStack = new Stack<>(); // descreasing
        final int n = nums.length;
        monoStack.push(0);
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[monoStack.peek()]) monoStack.push(i);
        }

        int p = n - 1;
        int result = 0;
        while (p > 0) {
            final int num = nums[p];
            while (!monoStack.isEmpty() && nums[monoStack.peek()] <= num) {
                result = Math.max(result, p - monoStack.pop());
            }
            p--;
        }

        return result;
    }

    public static void main(String[] args) {
        Q962 q962 = new Q962();
        System.out.println(q962.maxWidthRamp(new int[]{6,0,8,2,1,5}));
    }
}
