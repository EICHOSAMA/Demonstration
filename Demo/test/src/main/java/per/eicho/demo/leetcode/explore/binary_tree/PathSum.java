package per.eicho.demo.leetcode.explore.binary_tree;

import per.eicho.demo.leetcode.datastructure.TreeNode;

final class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // The number of nodes in the tree is in the range [0, 5000].
        // -1000 <= Node.val <= 1000
        // -1000 <= targetSum <= 1000
        if (root == null) return false; // [], 0 → false
        final int subTarget = targetSum - root.val;
        return hasPathSum(root.left, subTarget) || hasPathSum(root.right, subTarget);
    }
}
