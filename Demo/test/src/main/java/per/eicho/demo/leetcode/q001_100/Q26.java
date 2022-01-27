package per.eicho.demo.leetcode.q001_100;

/**
 * <p>26. Remove Duplicates from Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/">26. Remove Duplicates from Sorted Array</a>
 */
public final class Q26 {
    public int removeDuplicates(int[] nums) {
        // 1. 0 <= nums.length <= 3 * 10^4
        // 2. -100 <= nums[i] <= 100
        // 3. nums is sorted in non-decreasing order.
        if (nums.length == 0) return 0;

        int lastIndex = 0;
        for (int i = 1; i < nums.length ; i++) {
            if (nums[i] != nums[lastIndex]) {
                nums[++lastIndex] = nums[i];
            }
        }
        return ++lastIndex;
    }
}
