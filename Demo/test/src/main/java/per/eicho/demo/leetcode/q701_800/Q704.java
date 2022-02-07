package per.eicho.demo.leetcode.q701_800;

/**
 * <p>704. Binary Search 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-search/">704. Binary Search</a>
 */
public final class Q704 {
    public int search(int[] nums, int target) {
        // 1. 1 <= nums.length <= 10^4
        // 2. -10^4 < nums[i], target < 10^4
        // 3. All the integers in nums are unique.
        // 4. nums is sorted in ascending order.        
        return binarySearch(nums, target, 0, nums.length - 1);
    }
    private int binarySearch(final int[] nums, final int target, int l, int r) {
        if (l == r) return nums[l] == target ? l : -1;

        final int mid = (l + r + 1) / 2;
        final int num = nums[mid];

        if (target < num) return binarySearch(nums, target, l, mid - 1);
        return binarySearch(nums, target, mid, r);
    }
}
