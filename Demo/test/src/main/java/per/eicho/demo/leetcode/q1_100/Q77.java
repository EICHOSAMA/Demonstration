package per.eicho.demo.leetcode.q1_100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>77. Combinations 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/combinations/">77. Combinations</a>
 */
final public class Q77 {

    private static class ProcessContext {
        final List<List<Integer>> result;

        final List<Integer> nums;

        final Stack<Integer> stack;

        final int n;
        final int k;

        ProcessContext(int n, int k) {
            result = new ArrayList<>(); 
        
            this.n = n;
            this.k = k;


            // 1. boxing
            nums = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) {
                nums.add(i);
            }

            stack = new Stack<>();
        }

        void add(int index) {
            stack.add(nums.get(index));
        }

        void pop() {
            stack.pop();
        }

        void save() {
            result.add(new ArrayList<>(stack));
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        //assert 1 <= k && k <= n && n <= 20;
        final ProcessContext context = new ProcessContext(n, k);
        search(context, 0, context.k);
        return context.result;
    }

    private void search(final ProcessContext context, final int index, final int left) {
        if (left == 0) {
            context.save();
            return;
        }

        // [performance optimization]
        if (index + left > context.n) {
            return;
        }

        // 1. use 
        context.add(index);
        search(context, index + 1, left - 1);
        context.pop();

        // 2. or not
        search(context, index + 1, left);
    }
}
