package per.eicho.demo.leetcode.q501_600;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>538. Convert BST to Greater Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-bst-to-greater-tree/">538. Convert BST to Greater Tree</a>
 */
public final class Q538 {
    public TreeNode convertBST(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [0, 10^4].
        // 2. -10^4 <= Node.val <= 10^4
        // 3. All the values in the tree are unique.
        // 4. root is guaranteed to be a valid binary search tree.
        traverse(root, 0);
        return root;
    }

    private int traverse(TreeNode node, int current) {
        if (node == null) return current;
        int sum = traverse(node.right, current);
        node.val = sum + node.val;
        return traverse(node.left, node.val);
    }
}
