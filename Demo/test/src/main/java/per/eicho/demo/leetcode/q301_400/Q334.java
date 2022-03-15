package per.eicho.demo.leetcode.q301_400;

/**
 * <p>334. Increasing Triplet Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/increasing-triplet-subsequence/">
 *   334. Increasing Triplet Subsequence</a>
 */
public final class Q334 {
    public boolean increasingTriplet(int[] nums) {
        // 1. 1 <= nums.length <= 5 * 10^5
        // 2. -2^31 <= nums[i] <= 2^31 - 1        
        final int n = nums.length;

        // Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
        final long DEFAULT_VALUE = (long)Integer.MAX_VALUE + 1L; 
        long min = nums[0];
        long[] min2 = new long[]{min, DEFAULT_VALUE} ;

        for (int i = 1; i < n; i++) {
            final int num = nums[i];

            // update min
            if (num <= min) {
                min = num;
                continue;
            }
            
            // assert num > min
            if (num <= min2[1]) {
                min2[0] = min;
                min2[1] = num;
                continue;
            }

            // assert num > min2[1]
            return true;
        }

        return false;
    }
}
