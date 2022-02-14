package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>112. Path Sum 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/path-sum/">112. Path Sum</a>
 */
public final class Q112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        // 1. The number of nodes in the tree is in the range [0, 5000].
        // 2. -1000 <= Node.val <= 1000
        // 3. -1000 <= targetSum <= 1000        
        if (root == null) return false;
        if (sum == root.val && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, sum - root.val) ||
               hasPathSum(root.right, sum - root.val);
    }
}
