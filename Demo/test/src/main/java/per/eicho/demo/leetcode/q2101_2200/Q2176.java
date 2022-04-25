package per.eicho.demo.leetcode.q2101_2200;

/**
 * <p>2176. Count Equal and Divisible Pairs in an Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/">
 *   2176. Count Equal and Divisible Pairs in an Array</a>
 */
public final class Q2176 {
    public int countPairs(int[] nums, int k) {
        // 1. 1 <= nums.length <= 100
        // 2. 1 <= nums[i], k <= 100
        final int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((i * j) % k != 0) continue;
                if (nums[i] != nums[j]) continue;
                count++;
            }
        }        
        return count;
    }
}
