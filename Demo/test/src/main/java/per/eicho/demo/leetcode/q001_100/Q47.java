package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>47. Permutations II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/permutations-ii/">47. Permutations II</a>
 */
final public class Q47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        // [pre-processing]: sort.
        Arrays.sort(nums);

        final List<List<Integer>> result = new ArrayList<>();

        // [pre-processing]: find out all candidates. 
        final List<Integer> candidates = new LinkedList<>();
        for (int num: nums) {
            candidates.add(num);
        }

        // main process
        final List<Integer> permutation = new ArrayList<>();
        process(result, candidates, permutation, nums.length);

        return result;
    }

    private void process(final List<List<Integer>> result, final List<Integer> candidates, final List<Integer> permutation, final int layer) {
        if (layer == 0) {
            result.add(new ArrayList<>(permutation));
            return;
        }

        assert layer != 0: "checked";

        Integer lastCandidate = null;
        final int size = candidates.size();
        for (int i = 0; i < size; i++) {
            final Integer candidate = candidates.get(i);

            if (lastCandidate == candidate) {
                continue;
            }

            lastCandidate = candidate;
            permutation.add(candidate);
            candidates.remove(i);
            process(result, candidates, permutation, layer - 1);
            candidates.add(i, candidate);
            permutation.remove(permutation.size() - 1); // remove last.
        }
    }
}
