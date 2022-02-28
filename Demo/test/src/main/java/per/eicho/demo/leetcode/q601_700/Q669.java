package per.eicho.demo.leetcode.q601_700;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>669. Trim a Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/trim-a-binary-search-tree/">
 *   669. Trim a Binary Search Tree</a>
 */
public final class Q669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 1. The number of nodes in the tree in the range [1, 10^4].
        // 2. 0 <= Node.val <= 10^4
        // 3. The value of each node in the tree is unique.
        // 4. root is guaranteed to be a valid binary search tree.
        // 5. 0 <= low <= high <= 104
        if (root == null) return null;

        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
