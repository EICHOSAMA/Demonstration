package per.eicho.demo.leetcode.q801_900;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>814. Binary Tree Pruning 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-pruning/">
 *   814. Binary Tree Pruning</a>
 */
public final class Q814 {
    public TreeNode pruneTree(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 200].
        // 2. Node.val is either 0 or 1.        
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val != 1) return null;
        return root;
    }
}
