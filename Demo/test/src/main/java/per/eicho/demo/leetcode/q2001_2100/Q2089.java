package per.eicho.demo.leetcode.q2001_2100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>2089. Find Target Indices After Sorting Array 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-target-indices-after-sorting-array/">2089. Find Target Indices After Sorting Array</a>
 */
public final class Q2089 {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        final List<Integer> result = new LinkedList<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) result.add(i);
        }

        return result;
    }
}
