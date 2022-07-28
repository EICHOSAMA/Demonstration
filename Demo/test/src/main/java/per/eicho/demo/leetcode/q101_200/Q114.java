package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>114. Flatten Binary Tree to Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/">114. Flatten Binary Tree to Linked List</a>
 */
public final class Q114 {
    public void flatten(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 2000].
        // 2. -100 <= Node.val <= 100
        TreeNode cursor = root;
        while (cursor != null) {
            if (cursor.left != null) {
                TreeNode rmn = cursor.left; // rmn: right most node.

                while (rmn.right != null && rmn.right != cursor) rmn = rmn.right;
                
                if (rmn.right == null) {
                    rmn.right = cursor;
                    cursor = cursor.left;
                } else {
                    rmn.right = cursor.right;
                    cursor.right = cursor.left;
                    cursor.left = null;
                    cursor = rmn.right;
                }
            } else {
                cursor = cursor.right;
            }
        }
    }
}
