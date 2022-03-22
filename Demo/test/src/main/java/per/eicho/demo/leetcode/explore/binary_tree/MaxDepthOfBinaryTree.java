package per.eicho.demo.leetcode.explore.binary_tree;

import per.eicho.demo.leetcode.datastructure.TreeNode;

final class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
