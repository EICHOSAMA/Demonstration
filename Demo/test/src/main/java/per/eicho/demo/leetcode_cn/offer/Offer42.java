package per.eicho.demo.leetcode_cn.offer;

/**
 * <p>剑指 Offer 42. 连续子数组的最大和 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/">
 *   剑指 Offer 42. 连续子数组的最大和</a>
 */
public final class Offer42 {

    public int maxSubArray(int[] nums) {
        // 1. 1 <= arr.length <= 10^5
        // 2. -100 <= arr[i] <= 100
        final int n = nums.length;
        int f = nums[0];
        int result = f;
        for (int i = 1; i < n; i++) {
            final int num = nums[i];

            f = Math.max(f + num, num);
            result = Math.max(result, f);
        }

        return result;
    }
}
