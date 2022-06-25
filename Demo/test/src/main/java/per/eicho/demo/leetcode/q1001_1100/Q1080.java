package per.eicho.demo.leetcode.q1001_1100;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1080. Insufficient Nodes in Root to Leaf Paths 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/">
 *   1080. Insufficient Nodes in Root to Leaf Paths</a>
 */
public final class Q1080 {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        // 1. The number of nodes in the tree is in the range [1, 5000].
        // 2. -10^5 <= Node.val <= 10^5
        // 3. -10^9 <= limit <= 10^9
        final int max = traversal(root, limit, 0);
        return max >= limit ? root : null;
    }

    private int traversal(TreeNode node, int limit, int sum) {
        if (node.left == null && node.right == null) return sum + node.val;

        // A node is insufficient if every root to leaf path intersecting 
        // this node has a sum strictly less than limit.
        int maxSum = Integer.MIN_VALUE;
        if (node.left != null) {
            final int maxSumL = traversal(node.left, limit, sum + node.val);
            if (maxSumL < limit) node.left = null;  
            maxSum = maxSumL;
        }

        if (node.right != null) {
            final int maxSumR = traversal(node.right, limit, sum + node.val);
            if (maxSumR < limit) node.right = null;
            maxSum = Math.max(maxSum, maxSumR);
        }

        return maxSum;
    }
}
