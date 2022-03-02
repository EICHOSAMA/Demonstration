package per.eicho.demo.leetcode.q201_300;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>222. Count Complete Tree Nodes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/count-complete-tree-nodes/">222. Count Complete Tree Nodes</a>
 */
public final class Q222 {
    public int countNodes(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 5 * 10^4].
        // 2. 0 <= Node.val <= 5 * 10^4
        // 3. The tree is guaranteed to be complete.        
        // 4. Design an algorithm that runs in less than O(n) time complexity.
        if (root == null) return 0;
        int index = 0;
        TreeNode cursor = root;

        while (cursor.left != null) {

            int leftDepth = getDepth(cursor.left);
            int rightDepth = getDepth(cursor.right);

            if (leftDepth > rightDepth) {
                cursor = cursor.left;
                index = index * 2 + 1;
            } else {
                cursor = cursor.right;
                index = index * 2 + 2;
            }
        }

        return index + 1;
    }

    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }
}
