package per.eicho.demo.leetcode.q901_1000;

/**
 * <p>922. Sort Array By Parity II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-array-by-parity-ii/">922. Sort Array By Parity II</a>
 */
public final class Q922 {
    public int[] sortArrayByParityII(int[] nums) {
        // 1. 2 <= nums.length <= 2 * 10^4
        // 2. nums.length is even.
        // 3. Half of the integers in nums are even.
        // 4. 0 <= nums[i] <= 1000
        final int n = nums.length / 2;
        
        final int[] oddNums = new int[n];
        final int[] evenNums = new int[n];

        int pOdd = 0, pEven = 0;
        for (int num : nums) {
            if (num % 2 == 1) {
                oddNums[pOdd++] = num;
            } else {
                evenNums[pEven++] = num;
            }
        }
        
        for (int i = 0; i < n; i++) {
            nums[i * 2] = evenNums[i];
            nums[i * 2 + 1] = oddNums[i];
        }

        return nums;
    }
}
