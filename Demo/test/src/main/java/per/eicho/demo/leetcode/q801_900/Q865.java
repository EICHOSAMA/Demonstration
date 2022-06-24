package per.eicho.demo.leetcode.q801_900;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>865. Smallest Subtree with all the Deepest Nodes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/">
 *   865. Smallest Subtree with all the Deepest Nodes</a>
 */
public final class Q865 {
    int[] depth = new int[501]; // 0 <= Node.val <= 500

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // 1. The number of nodes in the tree will be in the range [1, 500].
        // 2. 0 <= Node.val <= 500
        // 3. The values of the nodes in the tree are unique.        
        traversal(root);
        return search(root);
    }

    private TreeNode search(TreeNode node) {
        if (node.left == null && node.right == null) return node;
        if (node.left == null) return search(node.right);
        if (node.right == null) return search(node.left);
        if (depth[node.left.val] == depth[node.right.val]) return node;
        return search(depth[node.left.val] > depth[node.right.val] ? node.left : node.right);
    }

    private int traversal(TreeNode node) {
        if (node == null) return 0;
        return depth[node.val] = Math.max(traversal(node.left), traversal(node.right)) + 1;
    }
}
