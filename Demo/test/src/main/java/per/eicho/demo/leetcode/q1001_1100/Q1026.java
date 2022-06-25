package per.eicho.demo.leetcode.q1001_1100;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1026. Maximum Difference Between Node and Ancestor 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/">
 *   1026. Maximum Difference Between Node and Ancestor</a>
 */
public final class Q1026 {

    int maxDiff = 0;

    public int maxAncestorDiff(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [2, 5000].
        // 2. 0 <= Node.val <= 10^5
        traversal(root, new int[]{0, 0});
        return maxDiff;
    }

    private void traversal(TreeNode node, int[] maxmin) {
        if (node.left == null && node.right == null) {
            maxmin[0] = node.val;
            maxmin[1] = node.val;
            return;
        }

        final int val = node.val;
        int min = val;
        int max = val;
        if (node.left != null) {
            traversal(node.left, maxmin);
            maxDiff = Math.max(maxDiff, 
                Math.max(Math.abs(val - maxmin[0]), Math.abs(val - maxmin[1])));
            min = Math.min(min, maxmin[0]);
            max = Math.max(max, maxmin[1]);
        }

        if (node.right != null) {
            traversal(node.right, maxmin);
            maxDiff = Math.max(maxDiff, 
                Math.max(Math.abs(val - maxmin[0]), Math.abs(val - maxmin[1])));
            min = Math.min(min, maxmin[0]);
            max = Math.max(max, maxmin[1]);
        }

        maxmin[0] = min;
        maxmin[1] = max;
    }
}
