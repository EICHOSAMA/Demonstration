package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>110. Balanced Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/balanced-binary-tree/">110. Balanced Binary Tree</a>
 */
public final class Q110 {
    private static final int TREE_IS_NOT_BALANCE = -1;

    public boolean isBalanced(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 5000].
        // 2. -10^4 <= Node.val <= 10^4        
        return getHeightIfRootIsBalance(root) != TREE_IS_NOT_BALANCE;
    }

    private int getHeightIfRootIsBalance(final TreeNode root) {
        if (root == null) return 0;
        final int left = getHeightIfRootIsBalance(root.left);
        if (left == TREE_IS_NOT_BALANCE) return TREE_IS_NOT_BALANCE;
        final int right = getHeightIfRootIsBalance(root.right);
        if (right == TREE_IS_NOT_BALANCE) return TREE_IS_NOT_BALANCE;
        return (Math.abs(left - right) <= 1)? Math.max(left, right) + 1: TREE_IS_NOT_BALANCE;
    }
}
