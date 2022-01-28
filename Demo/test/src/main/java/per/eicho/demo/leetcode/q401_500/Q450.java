package per.eicho.demo.leetcode.q401_500;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>450. Delete Node in a BST 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/delete-node-in-a-bst/">450. Delete Node in a BST</a>
 */
public final class Q450 {

    boolean deleted = false;

    public TreeNode deleteNode(TreeNode root, int key) {
        // 1. The number of nodes in the tree is in the range [0, 10^4].
        // 2. -10^5 <= Node.val <= 10^5
        // 3. Each node has a unique value.
        // 4. root is a valid binary search tree.
        // 5. -10^5 <= key <= 10^5
        if (root == null) return null;

        // Search for a node to remove.
        // If the node is found, delete the node.

        if (root.val == key) {
            deleted = true;
            return deleteRoot(root);
        }

        if (!deleted) root.left = deleteNode(root.left, key);
        if (!deleted) root.right = deleteNode(root.right, key);
        return root;
    }

    private TreeNode deleteRoot(TreeNode root) {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        final TreeNode originalLeftRoot = root.left;
        final TreeNode originalRightRoot = root.right;
        root.left = null; root.right = null;

        if (originalRightRoot.left == null) {
            originalRightRoot.left = originalLeftRoot;
            return originalRightRoot;
        }

        TreeNode cursor = originalRightRoot;
        while (cursor.left.left != null) {
            cursor = cursor.left;
        }

        final TreeNode newRoot = cursor.left;
        cursor.left = newRoot.right;
        newRoot.left = originalLeftRoot;
        newRoot.right = originalRightRoot;
        return newRoot;
    }
    
}
