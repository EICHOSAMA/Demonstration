package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>908. Smallest Range I 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-range-i/">908. Smallest Range I</a>
 */
public final class Q908 {
    public int smallestRangeI(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^4
        // 2. 0 <= nums[i] <= 10^4
        // 3. 0 <= k <= 10^4        
        final int n = nums.length;
        int result = Integer.MAX_VALUE;

        int max = nums[0];
        int min = max;
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        return (result = (max - min) - 2 * k) < 0 ? 0 : result;
    }
}
