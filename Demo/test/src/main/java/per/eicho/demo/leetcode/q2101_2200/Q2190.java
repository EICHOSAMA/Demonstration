package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2190. Most Frequent Number Following Key In an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/most-frequent-number-following-key-in-an-array/">
 *   2190. Most Frequent Number Following Key In an Array</a>
 */
public final class Q2190 {
    public int mostFrequent(int[] nums, int key) {
        // 1. 2 <= nums.length <= 1000
        // 2. 1 <= nums[i] <= 1000
        // 3. The test cases will be generated such that the answer is unique.        
        final int[] count = new int[1001];
        final int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != key) continue;
            count[nums[i + 1]]++; 
        }

        int max = 0;
        int result = 0;
        for (int i = 1; i <= 1000; i++) {
            if (count[i] > max) {
                max = count[i];
                result = i;
            }
        }

        return result;
    }
}
