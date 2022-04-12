package per.eicho.demo.leetcode.q301_400;

/**
 * <p>303. Range Sum Query - Immutable 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/range-sum-query-immutable/">
 *   303. Range Sum Query - Immutable</a>
 */
public final class Q303 {
    class NumArray {

        int[] sums;
    
        /** Initializes the object with the integer array nums. */
        NumArray(int[] nums) {
            sums = nums;
            for (int i = 1; i < nums.length; i++) {
                sums[i] += sums[i-1];
            }
        }
    
        /** 
         * Returns the sum of the elements of nums between indices left and right inclusive 
         *   (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
         */
        public int sumRange(int i, int j) {
            return sums[j] - (i == 0 ? 0 : sums[i-1]);
        }
    }
}
