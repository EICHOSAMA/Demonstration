package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>617. Merge Two Binary Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-two-binary-trees/">617. Merge Two Binary Trees</a>
 */
public final class Q617 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 1. The number of nodes in both trees is in the range [0, 2000].
        // 2. -10^4 <= Node.val <= 10^4
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
