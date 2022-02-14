package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>100. Same Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/same-tree/">100. Same Tree</a>
 */
public final class Q100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 1. The number of nodes in both trees is in the range [0, 100].
        // 2. -10^4 <= Node.val <= 10^4        
        if (p == q) return true; // null == null
        if (p == null || q == null) return false;
        return p.val == q.val &&
               isSameTree(p.right , q.right) &&
               isSameTree(p.left, q.left);   
    }
}
