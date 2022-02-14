package per.eicho.demo.leetcode.q001_100;

/**
 * <p>75. Sort Colors 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-colors/">75. Sort Colors</a>
 */
public final class Q75 {
    public void sortColors(int[] nums) {
        // 1. n == nums.length
        // 2. 1 <= n <= 300
        // 3. nums[i] is either 0, 1, or 2.        
        int l = -1, r = nums.length;
        int cursor = 0, numi;
        while (cursor < r) {
            numi = nums[cursor]; // processing number.
            if (numi == 0) {
                ++l;
                if (cursor != l) {
                    nums[cursor] = nums[l];
                    nums[l] = 0;
                } else {
                    cursor++;
                }
            } else if (numi == 2) {
                --r;
                if (cursor != r) {
                    nums[cursor] = nums[r];
                    nums[r] = 2;
                }
            } else {
                nums[cursor++] = 1;
            }
        }
    }
}
