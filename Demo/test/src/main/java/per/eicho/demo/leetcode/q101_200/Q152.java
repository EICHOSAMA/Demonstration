package per.eicho.demo.leetcode.q101_200;

/**
 * <p>152. Maximum Product Subarray 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-product-subarray/">152. Maximum Product Subarray</a>
 */
public final class Q152 {
    public int maxProduct(int[] nums) {
        // 1. 1 <= nums.length <= 2 * 10^4
        // 2. -10 <= nums[i] <= 10
        // 3. The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
        int maxProduct = nums[0];
        
        int[][] f = new int[nums.length][2];

        f[0][0] = nums[0];
        f[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            final int num = nums[i];
            final int max = f[i - 1][0];
            final int min = f[i - 1][1];
            
            f[i][0] = max(max * num, min * num, num);
            f[i][1] = min(max * num, min * num, num);
            
            maxProduct = Math.max(maxProduct, f[i][0]);
        }
        return maxProduct;
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
