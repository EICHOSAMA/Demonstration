package per.eicho.demo.leetcode.q1301_1400;

/**
 * <p>1365. How Many Numbers Are Smaller Than the Current Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/">
 *   1365. How Many Numbers Are Smaller Than the Current Number</a>
 */
public final class Q1365 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 1. 2 <= nums.length <= 500
        // 2. 0 <= nums[i] <= 100        
        final int[] buckets = new int[101];
        for (int num : nums) buckets[num]++;
        
        final int[] table = new int[101];
        for (int i = 1; i <= 100; i++) table[i] = table[i - 1] + buckets[i - 1];

        final int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) result[i] = table[nums[i]];
        return result;
    }
}
