package per.eicho.demo.leetcode.q001_100;

/**
 * <p>41. First Missing Positive 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/first-missing-positive/">41. First Missing Positive</a>
 */
public final class Q41 {
    public int firstMissingPositive(int[] nums) {
        // 1. 1 <= nums.length <= 5 * 10^5
        // 2. -2^31 <= nums[i] <= 2^31 - 1        
        if (nums.length == 0) return 1;
        
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];
            
            if (num <= 0) continue;
            if (num > nums.length) continue;
            if (nums[num-1] == num) continue;
            
            nums[i] ^= nums[num-1];
            nums[num-1] ^= nums[i];
            nums[i] ^= nums[num-1];
            
            i--;
        }
        
        int result = 0;
        while (result < nums.length && nums[result] == result + 1) result++;
       
        return result + 1;
    }
}
