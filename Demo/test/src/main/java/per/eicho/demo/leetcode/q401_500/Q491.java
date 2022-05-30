package per.eicho.demo.leetcode.q401_500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * <p>491. Increasing Subsequences 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/increasing-subsequences/">
 *   491. Increasing Subsequences</a>
 */
public final class Q491 {

    private static final class Trie {
        
        final int num;
        final Map<Integer, Trie> childrens;

        Trie(int num) {
            this.num = num;
            childrens = new HashMap<>();
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        // 1. 1 <= nums.length <= 15
        // 2. -100 <= nums[i] <= 100        
        List<List<Integer>> result = new LinkedList<>();
        final Trie root = new Trie(-101);
        final Stack<Trie> stack = new Stack<>();
        stack.add(root);

        buildTrie(nums, 0, stack);

        final LinkedList<Integer> temp = new LinkedList<>();
        for (Integer key : root.childrens.keySet()) {
            traversalTrie(root.childrens.get(key), result, temp);
        }

        return result;
    }

    private void buildTrie(int[] nums, int p, Stack<Trie> stack) {
        for (int i = p; i < nums.length; i++) {
            final int num = nums[i];
            if (stack.peek().num > num) continue;
            
            final Trie node = stack.peek();
            if (!node.childrens.containsKey(num)) node.childrens.put(num, new Trie(num));
            stack.add(node.childrens.get(num));
            buildTrie(nums, i + 1, stack);
            stack.pop();
        }
    }

    private void traversalTrie(Trie node, List<List<Integer>> result, LinkedList<Integer> temp) {
        temp.add(node.num);
        
        if (temp.size() >= 2) result.add(new ArrayList<>(temp));
        for (Integer key : node.childrens.keySet()) {
            traversalTrie(node.childrens.get(key), result, temp);
        }

        temp.removeLast();
    }
}
