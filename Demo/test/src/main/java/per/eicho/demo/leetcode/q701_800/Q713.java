package per.eicho.demo.leetcode.q701_800;

/**
 * <p>713. Subarray Product Less Than K 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subarray-product-less-than-k/">
 *   713. Subarray Product Less Than K</a>
 */
public final class Q713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 1. 1 <= nums.length <= 3 * 10^4
        // 2. 1 <= nums[i] <= 1000
        // 3. 0 <= k <= 10^6
        if (k <= 1) return 0;
        // 2 <= k <= 10^6
        final int n = nums.length;
        int result = 0;
        int l = 0, r = -1;
        int product = 1;
        while (r + 1 < n && nums[r + 1] * product < k) product *= nums[++r];

        while (l < n) {
            final int count = (r - l + 1);
            result += count;

            // find next window.
            if (count == 0) {
                l++; r++;
                product = 1;
            } else {
                product /= nums[l++];
            }
            while (r + 1 < n && nums[r + 1] * product < k) product *= nums[++r];
        }
        return result;
    }

    public static void main(String[] args) {
        Q713 q713 = new Q713();
        System.out.println(q713.numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100)); // 8
    }
}
