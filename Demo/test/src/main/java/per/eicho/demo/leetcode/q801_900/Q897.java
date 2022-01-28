package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>897. Increasing Order Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/increasing-order-search-tree/">
 *   897. Increasing Order Search Tree</a>
 */
public final class Q897 {
    public TreeNode increasingBST(TreeNode root) {
        // 1. The number of nodes in the given tree will be in the range [1, 100].
        // 2. 0 <= Node.val <= 1000
        final List<TreeNode> flattedTree = new ArrayList<>();
        inOrderTrversal(root, flattedTree);
        
        for (int i = 0; i < flattedTree.size() - 1; i++) {
            final TreeNode node = flattedTree.get(i);
            node.left = null;
            node.right = flattedTree.get(i + 1);
        }
        flattedTree.get(flattedTree.size() - 1).right = null;
        return flattedTree.get(0);
    }

    private void inOrderTrversal(TreeNode root, List<TreeNode> flattedTree) {
        if (root == null) return;
        inOrderTrversal(root.left, flattedTree);
        flattedTree.add(root);
        inOrderTrversal(root.right, flattedTree);
    }
}
