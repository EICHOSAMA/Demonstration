package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>525. Contiguous Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/contiguous-array/">525. Contiguous Array</a>
 */
public final class Q525 {
    public int findMaxLength(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. nums[i] is either 0 or 1.
        final int n = nums.length;
        final int[] indexes = new int[2 * n + 1];
        Arrays.fill(indexes, -2);
        indexes[n] = -1;
        
        int result = 0, offset = 0;

        for (int i = 0; i < n; i++) {
            offset += (nums[i] == 0 ? -1 : 1);
            final int idx = offset + n;

            // record first index;
            if (indexes[idx] < -1) {
                indexes[idx] = i;
                continue;
            }

            result = Math.max(result, i - indexes[idx]);
        }
        return result;
    }

    public static void main(String[] args) {
        Q525 q525 = new Q525();
        int result = q525.findMaxLength(new int[]{0,0,1,0,0,0,1,1});
        System.out.println(result);
    }
}
