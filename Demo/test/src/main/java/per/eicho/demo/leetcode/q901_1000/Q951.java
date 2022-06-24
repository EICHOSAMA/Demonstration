package per.eicho.demo.leetcode.q901_1000;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>951. Flip Equivalent Binary Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/flip-equivalent-binary-trees/">
 *   951. Flip Equivalent Binary Trees</a>
 */
public final class Q951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // 1. The number of nodes in each tree is in the range [0, 100].
        // 2. Each tree will have unique node values in the range [0, 99].            
        if (root1 == null && root2 != null) return false;
        if (root1 != null && root2 == null) return false;
        if (root1 == null && root2 == null) return true;
        if (root1.val != root2.val) return false;
        
        final TreeNode l1 = root1.left;
        final TreeNode r1 = root1.right;
        final TreeNode l2 = root2.left;
        final TreeNode r2 = root2.right;
        return (flipEquiv(l1, l2) && flipEquiv(r1, r2)) || 
               (flipEquiv(l1, r2) && flipEquiv(r1, l2));
    }
}
