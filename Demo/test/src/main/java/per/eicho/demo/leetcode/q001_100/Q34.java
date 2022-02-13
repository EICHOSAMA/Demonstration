package per.eicho.demo.leetcode.q001_100;

/**
 * <p>34. Find First and Last Position of Element in Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/">
 *   34. Find First and Last Position of Element in Sorted Array</a>
 */
public final class Q34 {
    public int[] searchRange(int[] nums, int target) {
        // 1. 0 <= nums.length <= 10^5
        // 2. -10^9 <= nums[i] <= 10^9
        // 3. nums is a non-decreasing array.
        // 4. -10^9 <= target <= 10^9        
        if (nums.length == 0) return new int[] {-1, -1};
        final int[] result = new int[2];
        result[0] = findFirst(nums, 0, nums.length - 1, target);
        result[1] = findLast(nums, 0, nums.length -1 , target);
        return result;
    }

    private int findFirst(final int[] nums, final int l, final int r, final int target) {
        if (l == r) {
            if (nums[l] != target)
                return -1;
            return l;
        }

        final int mid = (l + r) / 2;
        if (target <= nums[mid])
            return findFirst(nums, l, mid, target);
        else
            return findFirst(nums, mid + 1, r, target);
    }

    private int findLast(final int[] nums, final int l, final int r, final int target) {
        if (l == r) {
            if (nums[l] != target)
                return -1;
            return l;
        }

        final int mid = (l + r + 1) / 2;
        if (target < nums[mid])
            return findLast(nums, l, mid - 1, target);
        else
            return findLast(nums, mid, r, target);
    }
}
