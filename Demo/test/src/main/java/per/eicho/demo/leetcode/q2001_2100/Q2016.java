package per.eicho.demo.leetcode.q2001_2100;

/**
 * <p>2016. Maximum Difference Between Increasing Elements 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-difference-between-increasing-elements/">
 *   2016. Maximum Difference Between Increasing Elements</a>
 */
public final class Q2016 {
    public int maximumDifference(int[] nums) {
        // 1. n == nums.length
        // 2. 2 <= n <= 1000
        // 3. 1 <= nums[i] <= 10^9

        // find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), 
        // such that 0 <= i < j < n and nums[i] < nums[j].
        int result = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            final int numI = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                final int numJ = nums[j];
                if (numI >= numJ) continue;
                result = Math.max(result, numJ - numI);
            }
        }

        return result;
    }
}
