package per.eicho.demo.leetcode.explore.binary_tree;

import per.eicho.demo.leetcode.datastructure.TreeNode;

final class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l != null && r != null) 
            return l.val == r.val && isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
        return false;
    }
}
