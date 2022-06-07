package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>623. Add One Row to Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-one-row-to-tree/">623. Add One Row to Tree</a>
 */
public final class Q623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. The depth of the tree is in the range [1, 10^4].
        // 3. -100 <= Node.val <= 100
        // 4. -10^5 <= val <= 10^5
        // 5. 1 <= depth <= the depth of tree + 1
        return addOneRow(root, val, depth, true);
    }

    public TreeNode addOneRow(TreeNode node, int val, int depth, boolean isLeft) {
        if (depth == 1) {
            final TreeNode insertedNode = new TreeNode(val);
            if (isLeft) {
                insertedNode.left = node;
            } else {
                insertedNode.right = node;
            }
            return insertedNode;
        }
        if (node == null) return null;
        node.left = addOneRow(node.left, val, depth - 1, true);
        node.right = addOneRow(node.right, val, depth - 1, false);
        return node;
    }
}
