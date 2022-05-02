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
        final int[] result = new int[n];
        //Arrays.sort(nums);
        for (int i = 0, l = 0, r = n - 1; i < n; i++) {
            int num = nums[i];
            result[num % 2 == 0 ? l++ : r--] = num;
        }
        return result;    
    }
}
