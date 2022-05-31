package per.eicho.demo.leetcode.q501_600;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>515. Find Largest Value in Each Tree Row 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-largest-value-in-each-tree-row/">
 *   515. Find Largest Value in Each Tree Row</a>
 */
public final class Q515 {
    
    List<Integer> result;
    
    public List<Integer> largestValues(TreeNode root) {
        // 1. The number of nodes in the tree will be in the range [0, 10^4].
        // 2. -2^31 <= Node.val <= 2^31 - 1
        result = new ArrayList<>();
        traversal(root, 0);
        return result;
    }

    private void traversal(TreeNode node, final int lv) {
        if (node == null) return;

        if (lv == result.size()) {
            result.add(node.val);
        } else {
            result.set(lv, Math.max(result.get(lv), node.val));
        }

        traversal(node.left, lv + 1);
        traversal(node.right, lv + 1);
    }
}
