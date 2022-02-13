package per.eicho.demo.leetcode.q001_100;

/**
 * <p>35. Search Insert Position 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/search-insert-position/">
 *   35. Search Insert Position</a>
 */
public final class Q35 {
    public int searchInsert(int[] nums, int target) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        // 3. nums contains distinct values sorted in ascending order.
        // 4. -10^4 <= target <= 10^4        
        final int count = nums.length;
        if (count == 0 || target <= nums[0])
            return 0;
        int l = 0, r = count - 1, mid;
        while (l != r) {
            mid = (l + r + 1)/ 2;
            if (target < nums[mid])
                r = mid - 1;
            else
                l = mid;
        }
        return nums[l] == target ? l: l + 1;
    }
}
