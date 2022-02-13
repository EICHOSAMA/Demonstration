package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>78. Subsets 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subsets/">78. Subsets</a>
 */
public final class Q78 {
    public List<List<Integer>> subsets(int[] nums) {
        // 1. 1 <= nums.length <= 10
        // 2. -10 <= nums[i] <= 10
        // 3. All the numbers of nums are unique.        
        final List<List<Integer>> result = new LinkedList<>();
        final List<Integer> iNums = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) iNums.add(nums[i]);
        final LinkedList<Integer> solution = new LinkedList<>();
        for (int i = 0; i <= nums.length; i++) {
            search(result, iNums, 0, i, solution);    
        }
        return result;
    }

    private void search(List<List<Integer>> result, List<Integer> nums, int i, int remain, LinkedList<Integer> solution) {
        if (nums.size() - i < remain) return; // not enough element 
        if (remain == 0) {
            result.add(new ArrayList<>(solution));
            return;
        }

        // 1. use i-th num
        solution.add(nums.get(i));
        search(result, nums, i + 1, remain - 1, solution);
        solution.removeLast();

        // 2. not use i-th num
        search(result, nums, i + 1, remain, solution);
    }
}
