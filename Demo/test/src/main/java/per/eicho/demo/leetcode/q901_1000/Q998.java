package per.eicho.demo.leetcode.q901_1000;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>998. Maximum Binary Tree II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-binary-tree-ii/">
 *   998. Maximum Binary Tree II</a>
 */
public final class Q998 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        // 1. The number of nodes in the tree is in the range [1, 100].
        // 2. 1 <= Node.val <= 100
        // 3. All the values of the tree are unique.
        // 4. 1 <= val <= 100        
        if (root == null || val > root.val) {
            final TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
