package per.eicho.demo.leetcode.q601_700;

import java.util.Arrays;

/**
 * <p>611. Valid Triangle Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/valid-triangle-number/">
 *   611. Valid Triangle Number</a>
 */
public final class Q611 {
    public int triangleNumber(int[] nums) {
        // 1. 1 <= nums.length <= 1000
        // 2. 0 <= nums[i] <= 1000        
        final int n = nums.length;
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < n; i++) {
            final int a = nums[i];
            int k = i;
            for (int j = i + 1; j < n; j++) {
                while (k + 1 < n && nums[k + 1] < a + nums[j]) k++;
                result += Math.max(k - j, 0);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q611 q611 = new Q611();
        System.out.println(q611.triangleNumber(new int[]{0,1,2,3}));
    }
}
