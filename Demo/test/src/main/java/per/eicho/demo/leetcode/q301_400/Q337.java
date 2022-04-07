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

    private static final int ROBBED = 0;
    private static final int NOT_ROBBED = 1;

    public int rob(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. 0 <= Node.val <= 10^4        
        // rob current or not.        
        int[] f = dfs(root);
        return Math.max(f[ROBBED], f[NOT_ROBBED]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        final int[] l = dfs(node.left);
        final int[] r = dfs(node.right);
        return new int[]{
            node.val + l[NOT_ROBBED] + r[NOT_ROBBED], 
            Math.max(l[ROBBED], l[NOT_ROBBED]) + Math.max(r[ROBBED], r[NOT_ROBBED])};
    }
}
