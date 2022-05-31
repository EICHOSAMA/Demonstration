package per.eicho.demo.leetcode.q501_600;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>513. Find Bottom Left Tree Value 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-bottom-left-tree-value/">
 *   513. Find Bottom Left Tree Value</a>
 */
public final class Q513 {
    
    int lv = 0;
    int val;
    
    public int findBottomLeftValue(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. -2^31 <= Node.val <= 2^31 - 1
        val = root.val;
        traversal(root, 0);
        return val;
    }

    private void traversal(TreeNode node, final int lv) {
        if (node == null) return;

        if (node.left == null) {
            if (lv > this.lv) {
                this.lv = lv;
                this.val = node.val;     
            }
        }

        traversal(node.left, lv + 1);
        traversal(node.right, lv + 1);
    }
}
