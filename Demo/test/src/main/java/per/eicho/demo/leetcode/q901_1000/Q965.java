package per.eicho.demo.leetcode.q901_1000;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>965. Univalued Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/univalued-binary-tree/">965. Univalued Binary Tree</a>
 */
public final class Q965 {
    public boolean isUnivalTree(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 100].
        // 2. 0 <= Node.val < 100        
        if (root.left == null && root.right == null) return true;

        boolean checkLeft = true;
        if (root.left != null) checkLeft = root.val == root.left.val && isUnivalTree(root.left); 
        if (!checkLeft) return false;
        
        boolean checkRight = true;
        if (root.right != null) checkRight = root.val == root.right.val && isUnivalTree(root.right);
        if (!checkRight) return false;

        return true;
    }
}
