package per.eicho.demo.leetcode.q1601_1700;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>1695. Maximum Erasure Value 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-erasure-value/">
 *   1695. Maximum Erasure Value</a>
 */
public final class Q1695 {
    public int maximumUniqueSubarray(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. 1 <= nums[i] <= 10^4
        final int n = nums.length;
        final Set<Integer> numSet = new HashSet<>();
        numSet.add(nums[0]);
        int sum = nums[0];
        int result = nums[0];
        int l = 0, r = 0;

        while (r + 1 < n) {
            final Integer num = nums[++r];
            if (!numSet.contains(num)) {
                sum += num;
                numSet.add(num);
            } else {
                result = Math.max(result, sum);
                while (numSet.contains(num)) {
                    final Integer numL = nums[l++];
                    sum -= numL;
                    numSet.remove(numL);
                }
                r--;
            }
        }
        return result = Math.max(result, sum);
    }
}
