package per.eicho.demo.leetcode.q001_100;

/**
 * <p>31. Next Permutation 的题解代码 </p>
 * 
 * <pre>
 *  Implement next permutation, which rearranges numbers into the lexicographically 
 *  next greater permutation of numbers.
 *  If such an arrangement is not possible, it must rearrange it as the 
 *  lowest possible order (i.e., sorted in ascending order).
 *  
 *  The replacement must be in place and use only constant extra memory.
 * 
 * Example 1:
 *    Input: nums = [1,2,3]
 *    Output: [1,3,2]
 * 
 * Example 2:
 *    Input: nums = [3,2,1]
 *    Output: [1,2,3]
 * 
 * Example 3:
 *    Input: nums = [1,1,5]
 *    Output: [1,5,1]
 * 
 * Example 4:
 *    Input: nums = [1]
 *    Output: [1]
 *  
 * Constraints:
 *   1. 1 <= nums.length <= 100
 *   2. 0 <= nums[i] <= 100
 * </pre>
 * @see <a href="https://leetcode.com/problems/next-permutation/">31. Next Permutation</a>
 */
public class Q31 {
    public void nextPermutation(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. 0 <= nums[i] <= 100
        final int n = nums.length;
        
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--; 
        
        // n, n - 1, n - 2, ..., 1 → 1, 2, 3, 4, 5, 6.
        if (i < 0) {
            reverse(nums, 0);
            return;
        }

        final int num = nums[i];
        int j = n - 1;
        while (j >= 0 && num >= nums[j]) j--;
        swap(nums, i, j);
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
        return nums;
    }

    public static void main(String[] args) {
        Q31 q31 = new Q31();

        int[] nums = new int[]{10,9,8,7,6,5,4,3,2,1};
        q31.nextPermutation(nums);

        for (int i: nums) {
            System.out.print(i);
            System.out.print(",");
        }
    }
}
