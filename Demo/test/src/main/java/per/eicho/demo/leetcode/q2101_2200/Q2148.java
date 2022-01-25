package per.eicho.demo.leetcode.q2101_2200;

import java.util.Arrays;

/**
 * <p>2148. Count Elements With Strictly Smaller and Greater Elements 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-elements-with-strictly-smaller-and-greater-elements/">2148. Count Elements With Strictly Smaller and Greater Elements</a>
 */
public final class Q2148 {
    public int countElements(int[] nums) {
        // 1. 1 <= nums.length <= 100
        // 2. -10^5 <= nums[i] <= 10^5
        int result = 0;
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            final int num = nums[i];

            boolean hasSmallerNum = false;
            boolean hasBiggerNum = false;

            int j = 0;
            while (j < i) {
                if (nums[j] < num) {
                    hasSmallerNum = true;
                    break;
                }
                j++;
            }

            if (!hasSmallerNum) continue;

            j = nums.length - 1;
            while (j > i) {
                if (nums[j] > num) {
                    hasBiggerNum = true;
                    break;
                }
                j--;
            }

            if (hasBiggerNum && hasSmallerNum) result++;
        }
        return result;
    }
}
