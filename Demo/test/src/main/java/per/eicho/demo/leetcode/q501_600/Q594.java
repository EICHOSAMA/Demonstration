package per.eicho.demo.leetcode.q501_600;

import java.util.Arrays;

/**
 * <p>594. Longest Harmonious Subsequence 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-harmonious-subsequence/submissions/">594. Longest Harmonious Subsequence</a>
 */
final public class Q594 {
    public int findLHS(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int result = 0;
        Arrays.sort(nums);

        int lastNum = nums[0];
        int lastNumCount = 1;
        for (int i = 1; i < nums.length; i++) {
            final int num = nums[i];

            if (num == lastNum) {
                lastNumCount++;
                continue;
            }

            //assert num > lastNum: "sorted in ascending order";
            if (num - lastNum != 1) {
                lastNum = num;
                lastNumCount = 1;
                continue;
            } 

            //assert num - lastNum == 1: "validated";
            int j = i;
            int count = 0;
            while (j < nums.length && nums[j] == num) {
                count++;
                j++;
            }

            // update result if bigger than current result.
            result = Math.max(result, lastNumCount + count);

            // change status.
            lastNum = num;
            lastNumCount = count;
            i = i + count - 1;
        }

        return result;
    }
}
