package per.eicho.demo.leetcode.q201_300;

/**
 * <p>238. Product of Array Except Self 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/product-of-array-except-self/">
 *   238. Product of Array Except Self</a>
 */
public final class Q238 {
    public int[] productExceptSelf(int[] nums) {
        // 1. 2 <= nums.length <= 10^5
        // 2. -30 <= nums[i] <= 30
        // 3. The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.        
        final int n = nums.length;
        final int[] result = new int[n];

        int prefix = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }
}
