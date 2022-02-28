package per.eicho.demo.leetcode.q1001_1100;

import per.eicho.demo.leetcode.datastructure.TreeNode;
import per.eicho.demo.leetcode.q501_600.Q538;

/**
 * <p>1038. Binary Search Tree to Greater Sum Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/">
 *   1038. Binary Search Tree to Greater Sum Tree</a>
 * @see Q538 和Q538一毛一样
 */
public final class Q1038 {
    public TreeNode bstToGst(TreeNode root) {
        return convertBST(root);
    }
    
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
