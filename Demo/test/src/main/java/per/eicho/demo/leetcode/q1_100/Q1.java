package per.eicho.demo.leetcode.q1_100;

/**
 * 1. Two Sum 的题解代码
 * 
 * 
 * <pre>
 *  Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *  You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *  You can return the answer in any order.
 * </pre>
 * @see <a href="https://leetcode.com/problems/two-sum/">1. Two Sum</a>
 */
final public class Q1 {

    /**
     * One of the results of find method: nothing found.
     * @see #find(int[], int, int, int)
     */
    private final int SEARCH_RESULT_NOT_FOUND = -1;

    public int[] twoSum(int[] nums, int target) {
        final int count = nums.length;
        
        // 1. backup and sort original array.
        //  sorted array  : nums
        //  original array: backup
        int[] backup = new int[nums.length];
        copy(nums, backup);
        quickSort(nums, 0, count - 1);

        // 2. use sorted array to find result.
        final int[] indices = new int[2];
        int num1, remain, index2;
        for (int i = 0; i < count; i++) {
            num1 = nums[i];

            // [binary search] find if anohter number exists 
            remain = target - num1;
            index2 = find(nums, i + 1, count -1, remain);
            if (index2 != SEARCH_RESULT_NOT_FOUND) {
                indices[0] = i;
                indices[1] = index2;
                break;
            }
        }

        // 3. convert sorted array indices to original array indices.
        final int[] oldIndices = new int[2];
        int n1 = nums[indices[0]];
        int n2 = nums[indices[1]];
        int finded = 0;

        for (int i = 0; i < count; i++) {
            int n = backup[i];
            if (n == n1 || n == n2) {
                oldIndices[finded++] = i;
                if (finded >= 2)
                    break;
            }
        }
        return oldIndices;
    }
    
    /**
     * <p>QUICK SORT</p>
     * 
     * <pre>
     * Sort the given range [l, r] of the given array nums in ascending order.
     * </pre>
     * 
     * @param nums the array to be sorted.
     * @param l left index of process range. inclusive
     * @param r right index of process range. inclusive
     */
    private void quickSort(int[] nums, int l, int r) {
        if (nums == null || l >= r || nums.length <= 1) {
            return;
        }
        int i = l, j = r, pivot = nums[(l + r) / 2];
        while (i <= j) {
            while (nums[i] < pivot) {
                ++i;
            }
            while (nums[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        quickSort(nums, l, j);
        quickSort(nums, i, r);
    }

    /**
     * <p>BINARY SEARCH</p>
     * 
     * <pre>
     * search the index of the specific number targer in the given range [l, r] of 
     * given sorted array nums.
     * 
     * if found, then the index will be returned. 
     * if not found, {@link #SEARCH_RESULT_NOT_FOUND} will be returned.
     * </pre>
     * 
     * @param nums array which to be searched, must be sorted.
     * @param l left index of search range. inclusive
     * @param r right index of search range. inclusive
     * @param target the target number to search for.
     * @return
     */
    private int find(int[] nums, int l, int r, int target) {
        if (l > r)
            return SEARCH_RESULT_NOT_FOUND;

        if (target < nums[l] ||
            target > nums[r])
            return SEARCH_RESULT_NOT_FOUND;

        int mid = (l + r) / 2;
        int num = nums[mid];

        if (num == target)
            return mid;
        else if (num > target)
            return find(nums, l, mid - 1, target);
        else
            return find(nums, mid + 1, r, target);

    }

    /**
     * <p>
     * copy data
     * </p>
     * 
     * <pre>
     * copy all data from array FROM to array TO
     * </pre>
     * 
     * @param from data source, non-null
     * @param to destination, non-null, must have the same length with data source from.
     */
    private void copy(int[] from, int[] to) {
        assert to != null;
        assert from != null;
        assert from.length == to.length;

        int i = 0;
        for (int num: from) {
            to[i++] = num;
        }
    }
}
