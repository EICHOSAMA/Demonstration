package per.eicho.demo.leetcode.q1201_1300;

/**
 * <p>1295. Find Numbers with Even Number of Digits 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-numbers-with-even-number-of-digits/">
 *   1295. Find Numbers with Even Number of Digits</a>
 */
public final class Q1295 {
    public int findNumbers(int[] nums) {
        // 1. 1 <= nums.length <= 500
        // 2. 1 <= nums[i] <= 10^5
        int result = 0;
        for (int num : nums) {
            int count = 0;
            while (num != 0) {
                num /= 10;
                count++;
            }

            if (count % 2 == 0) result++;
        }
        
        return result;
    }
}
