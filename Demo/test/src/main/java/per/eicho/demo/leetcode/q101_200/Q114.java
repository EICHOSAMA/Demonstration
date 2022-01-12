package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>114. Flatten Binary Tree to Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/">114. Flatten Binary Tree to Linked List</a>
 */
public final class Q114 {
    public void flatten(TreeNode root) {
        flatTreeAndReturnLastNode(root);
    }

    private TreeNode flatTreeAndReturnLastNode(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;

        final TreeNode leftLast = flatTreeAndReturnLastNode(root.left);
        final TreeNode rightLast = flatTreeAndReturnLastNode(root.right);

        final TreeNode left = root.left;
        final TreeNode right = root.right;
        
        if (left != null) {
            root.right = left;
            leftLast.right = right;
            root.left = null;
        } else {
            root.right = right;
        }

        return rightLast != null ? rightLast : leftLast;
    }
}
