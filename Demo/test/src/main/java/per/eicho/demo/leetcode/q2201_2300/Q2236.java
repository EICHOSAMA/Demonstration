package per.eicho.demo.leetcode.q2201_2300;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>2236. Root Equals Sum of Children 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/root-equals-sum-of-children/">
 *   2236. Root Equals Sum of Children</a>
 */
public final class Q2236 {
    public boolean checkTree(TreeNode root) {
        // 1. The tree consists only of the root, its left child, and its right child.
        // 2. -100 <= Node.val <= 100        
        return root.val == root.left.val + root.right.val;
    }
}
