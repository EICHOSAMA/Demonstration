package per.eicho.demo.leetcode.q401_500;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>404. Sum of Left Leaves 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sum-of-left-leaves/">404. Sum of Left Leaves</a>
 */
public final class Q404 {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, false);    
    }

    private int sumOfLeftLeaves(TreeNode root, final boolean isLeft) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return isLeft ? root.val : 0;

        return sumOfLeftLeaves(root.left, true) + 
               sumOfLeftLeaves(root.right, false);
    }
}
