package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>905. Sort Array By Parity 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-array-by-parity/">
 *   905. Sort Array By Parity</a>
 */
public class Q905 {
    public int[] sortArrayByParity(int[] nums) {
        // 1. 1 <= nums.length <= 5000
        // 2. 0 <= nums[i] <= 5000
        final int n = nums.length;
        for (int l = 0, r = n - 1; l < r;) {
            if (nums[l] % 2 == 1) {
                swap(nums, l, r--);
            } else {
                l++;
            }
        }
        return nums;    
    }

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
