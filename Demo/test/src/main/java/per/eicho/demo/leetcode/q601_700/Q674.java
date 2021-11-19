package per.eicho.demo.leetcode.q601_700;

/**
 * <p>674. Longest Continuous Increasing Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-continuous-increasing-subsequence/">674. Longest Continuous Increasing Subsequence</a>
 */
public final class Q674 {
    public int findLengthOfLCIS(int[] nums) {
        // 1. [unsorted int array] Given an unsorted array of integers nums
        // 2. [output] return the length of the longest continuous increasing subsequence 
        // 3. [definition of LCIS] A continuous increasing subsequence is defined by two indices l and r (l < r) 
        //    such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].
        int l = 0;
        int r = 0;
        int result = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[r]) {
                r = i;
            } else {
                result = Math.max(result, r - l + 1);
                l = i;
                r = i;
            }
        }
        return Math.max(result, r - l + 1);
    }
}
