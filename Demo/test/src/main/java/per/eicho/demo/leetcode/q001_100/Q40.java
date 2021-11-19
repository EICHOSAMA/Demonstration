package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * <p>40. Combination Sum II 的题解代码 </p>
 * 
 * <pre>
 *  Given a collection of candidate numbers (candidates) and a target number (target), 
 *  find all unique combinations in candidates where the candidate numbers sum to target.
 *  Each number in candidates may only be used once in the combination.
 *  
 *  Note: The solution set must not contain duplicate combinations.
 * 
 * Example 1: 
 *    Input: candidates = [10,1,2,7,6,1,5], target = 8
 *    Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 * 
 * Example 2:
 *    Input: candidates = [2,5,2,1,2], target = 5
 *    Output: [[1,2,2],[5]]
 * 
 *  Constraints:
 *   1. 1 <= candidates.length <= 100
 *   2. 1 <= candidates[i] <= 50
 *   3. 1 <= target <= 30
 * </pre>
 * @see <a href="https://leetcode.com/problems/combination-sum-ii/">40. Combination Sum II</a>
 */
final public class Q40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 1. sort the given candidates array in ascending order and conver from array to list.
        final List<Integer> sortedCandidates = Arrays.stream(candidates)
            .sorted()
            .boxed()
            .collect(
                Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));

        return mainProcess(sortedCandidates, target);
    }

    private List<List<Integer>> mainProcess(final List<Integer> sortedCandidates, final int target) {
        final Stack<Integer> workspace = new Stack<Integer>();
        final Map<String, List<Integer>> solutionMap = new HashMap<String, List<Integer>>();
        
        doSearch(solutionMap, sortedCandidates, 0, target, workspace);

        final List<List<Integer>> solutions = new ArrayList<List<Integer>>(solutionMap.values()); 
        return solutions;
    }

    private void doSearch(final Map<String, List<Integer>> solutionMap, final List<Integer> sortedCandidates, final int i, final int target, final Stack<Integer> workspace) {
        if (0 == target) {
            // take a photo for workspace then store it in solutions.
            add2SolutionMap(solutionMap, workspace);
            return;
        }

        if (i >= sortedCandidates.size() || target < 0) {
            return;
        }
        
        // 1. use candidates[i] .
        final Integer num = sortedCandidates.get(i);
        workspace.push(num);
        doSearch(solutionMap, sortedCandidates, i + 1, target - num.intValue(), workspace);
        workspace.pop();

        // 2. do no use candidates[i].
        doSearch(solutionMap, sortedCandidates, i + 1, target, workspace);
    }

    private void add2SolutionMap(final Map<String, List<Integer>> solutionMap, final Stack<Integer> workspace) {
        final List<Integer> solution = new ArrayList<>(workspace);
        
        final String key =  solution.stream()
            .map(num -> num.toString())
            .collect(Collectors.joining(","));

        solutionMap.put(key, solution);
    }

    public static void main(String[] args) {
        Q40 q40 = new Q40();


        q40.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8).stream()
            .forEach(nums -> {
                nums.forEach(num -> System.out.print(num + ","));
                System.out.println(); 
            });;
    }
}
