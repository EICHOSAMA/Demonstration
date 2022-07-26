package per.eicho.demo.leetcode.q201_300;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>236. Lowest Common Ancestor of a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/">236. Lowest Common Ancestor of a Binary Tree</a>
 */
public final class Q236 {

    TreeNode p;
    TreeNode q;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. The number of nodes in the tree is in the range [2, 105].
        // 2. -10^9 <= Node.val <= 10^9
        // 3. All Node.val are unique.
        // 4. p != q
        // 5. p and q will exist in the tree.

        this.p = p;
        this.q = q;

        return postOrderTraversal(root, new int[]{0b00});
    }

    private TreeNode postOrderTraversal(TreeNode node, int[] mark) {
        if (node == null) return null;
        final int originalStatus = mark[0];

        
        TreeNode left = postOrderTraversal(node.left, mark);
        int searchLeft = mark[0];
        if (left != null) return left;

        mark[0] = originalStatus;
        TreeNode right = postOrderTraversal(node.right, mark);
        int searchRight = mark[0];
        if (right != null) return right;

        mark[0] = originalStatus | searchLeft | searchRight;

        if (node.val == q.val) {
            mark[0] |= 0b10;
        } else if (node.val == p.val) {
            mark[0] |= 0b01;
        }

        if (mark[0] == 0b11) return node;
        return null;
    }
}
