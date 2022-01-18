package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>124. Binary Tree Maximum Path Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/">124. Binary Tree Maximum Path Sum</a>
 */
public final class Q124 {

    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        /**
         * 1. The number of nodes in the tree is in the range [1, 3 * 10^4].
         * 2. -1000 <= Node.val <= 1000
         */

        traversal(root);
        
        return result;
    }

    private int traversal(TreeNode node) {
        if (node == null) return 0;

        int sum = node.val;

        final int left = traversal(node.left);
        final int right = traversal(node.right);

        if (left <= 0 && right <= 0) {
            updateResult(sum);
            return sum;
        }

        if (left > 0 && right > 0) {
            updateResult(sum + left + right);
        }

        sum += Math.max(left, right);
        updateResult(sum);
        return sum;
    }

    private int updateResult(int candidate) {
        return (result = Math.max(result, candidate));
    }
}
