package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>46. Permutations 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/permutations/">46. Permutations</a>
 */
public final class Q46 {
    public List<List<Integer>> permute(int[] nums) {
        // 1. 1 <= nums.length <= 6
        // 2. -10 <= nums[i] <= 10
        // 3. All the integers of nums are unique.
        
        // if (nums == null || nums.length == 0) return new ArrayList<>();

        final List<List<Integer>> result = new ArrayList<>(factorial(nums.length));
        final List<Integer> boxedNums = box(nums);
        final boolean[] useage = new boolean[nums.length];
        final List<Integer> currentSolution = new ArrayList<>(nums.length);

        search(result, useage, boxedNums, currentSolution, 0);
        return result;
    }

    private void search(final List<List<Integer>> result,
                        final boolean[] useage,
                        final List<Integer> nums,
                        final List<Integer> currentSolution,
                        final int index) {
        if (index == nums.size()) {
            result.add(new ArrayList<>(currentSolution)); // copy current solution and add to result.
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (useage[i] == false) {
                // num i is not used.
                currentSolution.add(nums.get(i));
                useage[i] = true;
                search(result, useage, nums, currentSolution, index + 1);
                useage[i] = false;
                currentSolution.remove(index);
            }
        }
    }


    private List<Integer> box(int[] nums) {
        assert nums != null;
        final List<Integer> boxedNums = new ArrayList<>(nums.length);
        for (int num: nums)
            boxedNums.add(num);
        return boxedNums;
    }

    private int factorial(int n) {
        assert n > 0;
        return n == 1? 1: factorial(n-1) * n;
    }
}
