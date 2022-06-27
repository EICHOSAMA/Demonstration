package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>915. Partition Array into Disjoint Intervals 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-array-into-disjoint-intervals/">
 *   915. Partition Array into Disjoint Intervals</a>
 */
public final class Q915 {
    public int partitionDisjoint(int[] nums) {
        // 1. 2 <= nums.length <= 10^5
        // 2. 0 <= nums[i] <= 10^6
        // 3. There is at least one valid answer for the given input.
        final int n = nums.length;
        final int[] min = new int[n];
        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) min[i] = Math.min(min[i + 1], nums[i]);

        int max = nums[0];
        int i = 1;
        while (max > min[i]) max = Math.max(max, nums[i++]);

        // Return the length of left after such a partitioning
        return i;
    }
}
