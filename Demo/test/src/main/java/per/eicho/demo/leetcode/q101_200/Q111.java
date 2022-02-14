package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>111. Minimum Depth of Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">111. Minimum Depth of Binary Tree</a>
 */
public final class Q111 {
    public int minDepth(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 105].
        // 2. -1000 <= Node.val <= 1000        
        if (root != null) {
            if (root.left != null && root.right != null)
                return Math.min(minDepth(root.right), minDepth(root.left)) + 1;
            if (root.left != null)
                return minDepth(root.left) + 1;
            if (root.right != null)
                return minDepth(root.right) + 1;
            return 1;
        }
        return 0;
    }
}
