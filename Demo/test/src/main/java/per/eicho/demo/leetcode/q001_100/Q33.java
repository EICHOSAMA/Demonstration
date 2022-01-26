package per.eicho.demo.leetcode.q001_100;


/**
 * <p>33. Search in Rotated Sorted Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">33. Search in Rotated Sorted Array</a>
 */
public final class Q33 {
    public int search(int[] nums, int target) {
        // 1. 1 <= nums.length <= 5000
        // 2. -104 <= nums[i] <= 104
        // 3. All values of nums are unique.
        // 4. nums is an ascending array that is possibly rotated.
        // 5. -10^4 <= target <= 10^4

        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return target == nums[0]? 0: -1;
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(final int[] nums, final int l, final int r, final int target) {
        if (l + 1 == r) {
            return nums[l] == target ? l :
                    (nums[r] == target ? r : -1);
        }

        final int mid = (l + r + 1) / 2;

        final int numL = nums[l];
        final int numMid = nums[mid];
        final int numR = nums[r];

        if (((numL < numMid) && (numL <= target && target <= numMid) ||
                (numMid < numR) && !(numMid <= target && target <= numR)))
            return binarySearch(nums, l, mid, target); // search lef
        return binarySearch(nums, mid, r, target); // search right
    }
}
