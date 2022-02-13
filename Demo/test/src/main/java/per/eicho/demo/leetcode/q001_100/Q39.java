package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

/**
 * <p>39. Combination Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum/">39. Combination Sum</a>
 */
public final class Q39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 1. 1 <= candidates.length <= 30
        // 2. 1 <= candidates[i] <= 200
        // 3. All elements of candidates are distinct.
        // 4. 1 <= target <= 500        
        List<List<Integer>> result = new ArrayList<>();

        SortedSet<Integer> sortedCandidateSet = new TreeSet<>();
        for (int candidate: candidates) {
            //assert candidate > 0: "All numbers (including target) will be positive integers.";

            if (candidate <= target)
                sortedCandidateSet.add(candidate);
        }

        search(new ArrayList<>(sortedCandidateSet),
                0,
                result,
                target,
                new Stack<>());
        return result;
    }

    private void search(final List<Integer> sortedCandidates,
                               final int i,
                               final List<List<Integer>> results,
                               final int target,
                               final Stack<Integer> solution) {
        if (i >= sortedCandidates.size()) return;
        if (target == 0) {
            List<Integer> result = new ArrayList<Integer>(solution);
            results.add(result);
            return;
        }
        if (sortedCandidates.get(i) > target) return;

        // pick current or move next.

        // 1 pick current
        solution.add(sortedCandidates.get(i));
        search(sortedCandidates, i, results, target - sortedCandidates.get(i), solution);
        solution.pop();

        // move to move to next.
        search(sortedCandidates, i+1, results, target, solution);
    }
}
