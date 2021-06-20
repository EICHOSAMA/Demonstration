package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>561. Array Partition I 的题解代码 </p>
 * 
 * <pre>
 *  Given an integer array nums of 2n integers, 
 *  group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of 
 *  min(ai, bi) for all i is maximized. Return the maximized sum.
 * 
 * Example 1:
 *    Input: nums = [1,4,3,2]
 *    Output: 4
 *    Explanation: All possible pairings (ignoring the ordering of elements) are:
 *      1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 *      2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 *      3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 *    So the maximum possible sum is 4.
 * 
 * Constraints:
 *    1. 1 <= n <= 10^4
 *    2. nums.length == 2 * n
 *    3. -10^4 <= nums[i] <= 10^4
 * 
 * </pre>
 * @see <a href="https://leetcode.com/problems/array-partition-i/">561. Array Partition I</a>
 */
final public class Q561 {
    public int arrayPairSum(int[] nums) {
        final int n = nums.length / 2;
        
        int result = 0;

        // 1. sort
        Arrays.sort(nums);

        // 2. main process
        for (int i = 0; i < n; i++) {
            result += nums[i * 2];
        }

        return result;
    }
}
