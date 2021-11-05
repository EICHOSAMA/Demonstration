package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>671. Second Minimum Node In a Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/">671. Second Minimum Node In a Binary Tree</a>
 */
public final class Q671 {
    public int findSecondMinimumValue(TreeNode root) {
        /*
          1. any node has exactly 0 or 2 sub-node.
          2. the property root.val = min(root.left.val, root.right.val) always holds.
          3. 1 <= Node.val <= 2^31 - 1
          */
        
        if (isLeafNode(root) == true) return -1;

        final int LEFT;
        final int RIGHT;
        if (root.left.val == root.right.val) {
            LEFT = findSecondMinimumValue(root.left);
            RIGHT = findSecondMinimumValue(root.right);
        } else if (root.left.val < root.right.val) {
            LEFT = findSecondMinimumValue(root.left);
            RIGHT = root.right.val;
        } else {
            LEFT = root.left.val;
            RIGHT = findSecondMinimumValue(root.right);
        }

        if (LEFT == -1) return RIGHT;
        if (RIGHT == -1) return LEFT;
        return Math.min(LEFT, RIGHT);
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
