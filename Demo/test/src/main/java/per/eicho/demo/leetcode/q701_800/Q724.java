package per.eicho.demo.leetcode.q701_800;

/**
 * <p>724. Find Pivot Index 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-pivot-index/">724. Find Pivot Index</a>
 */
public final class Q724 {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int l = 0, r = total;
        for (int i = 0; i < nums.length; i++) {
            r -= nums[i];
            if (l == r) {
                return i;
            }
            l += nums[i];
        }
        return -1;
    }
}
