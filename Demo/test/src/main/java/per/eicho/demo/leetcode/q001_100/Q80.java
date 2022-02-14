package per.eicho.demo.leetcode.q001_100;

/**
 * <p>80. Remove Duplicates from Sorted Array II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/">
 *  80. Remove Duplicates from Sorted Array II</a>
 */
public final class Q80 {
    public int removeDuplicates(int[] nums) {
        // 1. 1 <= nums.length <= 3 * 10^4
        // 2. -10^4 <= nums[i] <= 10^4
        // 3. nums is sorted in non-decreasing order.        
        final int len = nums.length;
        if (len == 0) return 0;

        int newLen = 1;
        int currentNum = nums[0];
        int currentNumCount = 1;

        int cursor = 1; // processing index.

        while (cursor < nums.length) {
            final int num = nums[cursor++];

            if (num == currentNum) {
                // two cases there.
                if (currentNumCount < 2) {
                    currentNumCount++;
                    nums[newLen++] = num;
                }
                // else skip, do nothing
            } else {
                // num != currentNum.
                nums[newLen++] = num;
                currentNum = num;
                currentNumCount = 1;
            }
        }
        return newLen;        
    }
}
