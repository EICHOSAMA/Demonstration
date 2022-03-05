package per.eicho.demo.leetcode.q201_300;

/**
 * <p>260. Single Number III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/single-number-iii/">
 *   260. Single Number III</a>
 */
public final class Q260 {
    public int[] singleNumber(int[] nums) {
        // 1. 2 <= nums.length <= 3 * 10^4
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        // 3. Each integer in nums will appear twice, only two integers will appear once.        
        final int n = nums.length;
        if (n == 2) return nums;
        final int[] result = new int[2];

        int aXorB = 0;
        for (int num : nums) {
            aXorB ^= num;
        }

        final int lastBit = aXorB & -aXorB;
        for (int num : nums) {
            if ((num & lastBit) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        return result;
    }
}
