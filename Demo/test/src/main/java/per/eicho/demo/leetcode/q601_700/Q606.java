package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>606. Construct String from Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/construct-string-from-binary-tree/">606. Construct String from Binary Tree</a>
 */
public final class Q606 {
    public String tree2str(TreeNode root) {
        if (root == null) return "";
        if (root.left == null && root.right == null) return "" + root.val;

        final String l = tree2str(root.left);
        final String r = tree2str(root.right);

        if (root.right == null) {
            return root.val + "(" + l + ")";
        }

        return root.val + "(" + l + ")" + "(" + r + ")";
    }
}
