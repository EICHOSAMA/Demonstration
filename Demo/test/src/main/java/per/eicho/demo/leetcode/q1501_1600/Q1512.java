package per.eicho.demo.leetcode.q1501_1600;

/**
 * <p>1512. Number of Good Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/number-of-good-pairs/">
 *   1512. Number of Good Pairs</a>
 */
public final class Q1512 {
    public int numIdenticalPairs(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. 1 <= nums[i] <= 100        
        int num = 0;
        final int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) num++;
            }
        }

        return num;
    }
}
