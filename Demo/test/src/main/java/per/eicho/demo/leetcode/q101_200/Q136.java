package per.eicho.demo.leetcode.q101_200;

/**
 * <p>136. Single Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/single-number/">136. Single Number</a>
 */
public final class Q136 {
    public int singleNumber(int[] nums) {
        // 1. 1 <= nums.length <= 3 * 10^4
        // 2. -3 * 10^4 <= nums[i] <= 3 * 10^4
        // 3. Each element in the array appears twice except for one element which appears only once.        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
