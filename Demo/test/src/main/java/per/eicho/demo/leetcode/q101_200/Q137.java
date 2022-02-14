package per.eicho.demo.leetcode.q101_200;

/**
 * <p>137. Single Number II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/single-number-ii/">137. Single Number II</a>
 */
public final class Q137 {
    public int singleNumber(int[] nums) {
        // 1. 1 <= nums.length <= 3 * 10^4
        // 2. -2^31 <= nums[i] <= 2^31 - 1
        // 3. Each element in nums appears exactly three times except for one element which appears once.        
        final int[][] counts = new int[8][16];
        final int MASK = 0b0000_1111;
        
        for(int num: nums) {
            for(int i = 0; i < 8; i++) {
                counts[i][num & MASK]++ ;
                num = num >>> 4;
            }
        }
        
        int result = 0;
        for(int i = 7; i >= 0; i--) {
            int j;
            for (j = 0b0000; j <= 0b1111; j++) {
                if (counts[i][j] % 3 != 0) break;
            }
            
            result <<= 4;
            result += j;
        }
        return result;
    }
}
