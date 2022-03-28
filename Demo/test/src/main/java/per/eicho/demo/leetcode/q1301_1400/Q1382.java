package per.eicho.demo.leetcode.q1301_1400;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1382. Balance a Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/balance-a-binary-search-tree/">
 *   1382. Balance a Binary Search Tree</a>
 */
public final class Q1382 {
    public TreeNode balanceBST(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 10^4].
        // 2. 1 <= Node.val <= 10^5
        final List<TreeNode> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return rebuild(nodes, 0, nodes.size() - 1);
    }

    private void inOrderTraversal(TreeNode node, List<TreeNode> output) {
        if (node == null) return;
        inOrderTraversal(node.left, output);
        output.add(node);
        inOrderTraversal(node.right, output);

        node.left = null;
        node.right = null;
    }

    private TreeNode rebuild(List<TreeNode> nodes, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        final TreeNode root = nodes.get(mid);
        root.left = rebuild(nodes, l, mid - 1);
        root.right = rebuild(nodes, mid + 1, r);
        return root;
    }
}
