package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>687. Longest Univalue Path 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/longest-univalue-path/">
 *   687. Longest Univalue Path</a>
 */
public final class Q687 {
    public int longestUnivaluePath(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 10^4].
        // 2. -1000 <= Node.val <= 1000
        // 3. The depth of the tree will not exceed 1000.
        if (root == null) return 0;
        return Math.max(longestUnivaluePath(root.left, root.val) + longestUnivaluePath(root.right, root.val),
               Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right)));
    }

    private int longestUnivaluePath(TreeNode node, int val) {
        if (node == null || node.val != val) return 0;
        return 1 + Math.max(longestUnivaluePath(node.left, val), longestUnivaluePath(node.right, val));
    }
}
