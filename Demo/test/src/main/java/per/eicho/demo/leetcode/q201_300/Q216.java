package per.eicho.demo.leetcode.q201_300;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * <p>216. Combination Sum III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/combination-sum-iii/">216. Combination Sum III</a>
 */
public final class Q216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 1. 2 <= k <= 9
        // 2. 1 <= n <= 60        
        final Stack<Integer> stack = new Stack<>();
        final List<List<Integer>> result = new LinkedList<>();
        final boolean[] marks = new boolean[10]; // [0, 9] 
        search(k, n, stack, result, marks);
        return result;
    }

    private void search(int k, int n, Stack<Integer> solution, List<List<Integer>> result, boolean[] marks) {
        if (n < 0) return;

        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(solution));
            return;
        }

        int top = 0;
        if (!solution.isEmpty()) top = solution.peek();

        for (int i = top + 1; i <= 9; i++) {
            if (marks[i] == true) continue;
            
            solution.add(i);
            marks[i] = true;
            search(k - 1, n - i, solution, result, marks);
            marks[i] = false;
            solution.pop();
        }
    }
}
