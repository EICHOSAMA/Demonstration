package per.eicho.demo.leetcode.q1301_1400;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1323. Maximum 69 Number 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-69-number/">
 *   1323. Maximum 69 Number</a>
 */
public final class Q1325 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // 1. The number of nodes in the tree is in the range [1, 3000].
        // 2. 1 <= Node.val, target <= 1000
        if (root == null) return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.val == target && root.left == null && root.right == null) return null;
        return root;
    }
}
