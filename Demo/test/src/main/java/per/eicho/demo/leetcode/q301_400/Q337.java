package per.eicho.demo.leetcode.q301_400;

import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>337. House Robber III 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/house-robber-iii/">337. House Robber III</a>
 */
public final class Q337 {

    Map<TreeNode, Map<Boolean, Integer>> f = new HashMap<>();

    public int rob(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. 0 <= Node.val <= 10^4        
        // rob current or not.
        return rob(root, Boolean.TRUE);
    }

    public int rob(TreeNode node, Boolean canRob) {
        if (node == null) return 0;

        if (f.containsKey(node) && f.get(node).containsKey(canRob)) return f.get(node).get(canRob);
        if (!f.containsKey(node)) f.put(node, new HashMap<>());
        
        int result;
        if (f.containsKey(node) && f.get(node).containsKey(Boolean.FALSE)) {
            result = f.get(node).get(Boolean.FALSE); 
        } else {
            result = rob(node.left, true) + rob(node.right, true);
            f.get(node).put(Boolean.FALSE, result);
        }


        if (canRob) {
            result = Math.max(result, node.val + rob(node.left, false) + rob(node.right, false));
            f.get(node).put(Boolean.TRUE, result);
        }

        return result;
    }
}
