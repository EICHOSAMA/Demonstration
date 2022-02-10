package per.eicho.demo.leetcode.q101_200;

/**
 * <p>189. Rotate Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rotate-array/">189. Rotate Array</a>
 */
public final class Q189 {
    public void rotate(int[] nums, int k) {
        // 1. 1 <= nums.length <= 10^5
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        // 0 <= k <= 10^5
        if (k > nums.length) k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k -1);
        reverse(nums, k, nums.length - 1);
    }
    
    public void reverse(final int[] nums, final int l, final int r) {
        final int mid = (r - l + 1)  / 2;
        for (int offset = 0; offset < mid; offset++) {
            nums[l + offset] ^= nums[r - offset];
            nums[r - offset] ^= nums[l + offset];
            nums[l + offset] ^= nums[r - offset];
        }
    }
}
