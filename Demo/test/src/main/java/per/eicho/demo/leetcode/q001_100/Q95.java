package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>95. Unique Binary Search Trees II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">95. Unique Binary Search Trees II</a>
 */
public final class Q95 {
    public List<TreeNode> generateTrees(int n) {
        // 1 <= n <= 8
        final List<Integer> candidates = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            candidates.add(i);
        }

        return generateTrees(candidates);
    }
    
    private List<TreeNode> generateTrees(final List<Integer> candidates) {
        final List<TreeNode> result = new ArrayList<>();
        
        final int count = candidates.size();
        if (count == 1) {
            result.add(new TreeNode(candidates.get(0)));
            return result; 
        }

        for (int i = 0; i < count; i++) {
            final int candidate = candidates.get(i);

            if (i == 0) {
                List<TreeNode> right = generateTrees(candidates.subList(i + 1, count));
                for (int j = 0; j < right.size(); j++) {
                    TreeNode root = new TreeNode(candidate);
                    root.right = right.get(j);
                    result.add(root);
                }
            } else if (i == count - 1) {
                List<TreeNode> left = generateTrees(candidates.subList(0, i));
                for (int j = 0; j < left.size(); j++) {
                    TreeNode root = new TreeNode(candidate);
                    root.left = left.get(j);
                    result.add(root);
                }
            } else {
                List<TreeNode> left = generateTrees(candidates.subList(0, i));
                List<TreeNode> right = generateTrees(candidates.subList(i + 1, count));
    
                for (int j = 0; j < left.size(); j++) {
                    TreeNode l = left.get(j);
                    for (int k = 0; k < right.size(); k++) {
                        TreeNode r = right.get(k);
                        TreeNode root = new TreeNode(candidate);
                        root.left = l;
                        root.right = r;
                        result.add(root);
                    }
                }
            }
        }
        return result;
    }
}
