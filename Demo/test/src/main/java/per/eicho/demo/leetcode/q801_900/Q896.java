package per.eicho.demo.leetcode.q801_900;

/**
 * <p>896. Monotonic Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/monotonic-array/">896. Monotonic Array</a>
 */
public final class Q896 {
    public boolean isMonotonic(int[] nums) {
        // 1. 1 <= nums.length <= 10^5
        // 2. -10^5 <= nums[i] <= 10^5
        final int n = nums.length;
        if (n == 1) return true;
        boolean increasing = nums[n - 1] - nums[0] >= 0;

        if (increasing) {
            for (int i = 1; i < n; i++) {
                if (nums[i] < nums[i - 1]) return false;
            }
        } else {
            for (int i = 1; i < n; i++) {
                if (nums[i] > nums[i - 1]) return false;
            }
        }
        return true;
    }
}
