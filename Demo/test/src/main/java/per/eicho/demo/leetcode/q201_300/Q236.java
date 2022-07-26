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
    TreeNode result;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. The number of nodes in the tree is in the range [2, 105].
        // 2. -10^9 <= Node.val <= 10^9
        // 3. All Node.val are unique.
        // 4. p != q
        // 5. p and q will exist in the tree.

        this.p = p;
        this.q = q;
        postOrderTraversal(root);
        return result;
    }

    private int postOrderTraversal(TreeNode node) {
        if (node == null) return 0b00;

        int left = postOrderTraversal(node.left);
        if (left == 0b11) return 0b11;

        int right = postOrderTraversal(node.right);
        if (right == 0b11) return 0b11;

        int status = left | right;
        if (node.val == q.val) {
            status |= 0b10;
        } else if (node.val == p.val) {
            status |= 0b01;
        }

        if (status == 0b11) result = node;
        return status;
    }
}
