package per.eicho.demo.leetcode.q1_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>90. Subsets II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/subsets-ii/">90. Subsets II</a>
 */
final public class Q90 {
    
    private static class ProcessingContext {
        final List<Integer> nums;
        final int count;
        final Map<String, List<Integer>> resultMap;


        /**
         * Searcing WorkSpace
         */
        final Stack<Integer> stack;

        ProcessingContext(int[] nums) {
            this.nums = IntStream.of(nums)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

            count = this.nums.size();
            
            resultMap = new HashMap<>();

            stack = new Stack<>();
            
        }

        void clearWorkspace() {
            stack.clear();
        }

        void append(int index) {
            stack.add(nums.get(index));
        }

        void pop() {
            stack.pop();
        }

        void save() {
            // take a photo of current worksapce.
            // and save it to the answer.
            final List<Integer> photo = new ArrayList<>(stack);
            final String key = toKey(photo);

            if (!resultMap.containsKey(key)) {
                resultMap.put(key, photo);
            }
        }

        private String toKey(final List<Integer> photo) {
            assert photo != null;
            final StringBuilder sb = new StringBuilder();
            
            for (Integer num : photo) {
                sb.append(num).append('.');
            }
            return sb.toString();
        }

        private List<List<Integer>> getResult() {
            return new ArrayList<>(resultMap.values());
        }
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        final ProcessingContext context = new ProcessingContext(nums);

        for (int count = 0; count <= context.count; count++) {
            context.clearWorkspace();
            search(context, 0, count);
        }

        return context.getResult();
    }

    private void search(final ProcessingContext context, int i, int count) {
        
        if (count == 0) {
            // save current workspace.
            context.save();
            return;
        }

        // [performance optimization]
        // i.e. i = 5, count = 2, context.count = 7 (idx [0, 6])
        if (i + count > context.count) {
            return;
        }

        // 1. use current
        context.append(i);
        search(context, i + 1, count - 1);
        context.pop();

        // 2. not use
        search(context, i + 1, count);
    }

}
