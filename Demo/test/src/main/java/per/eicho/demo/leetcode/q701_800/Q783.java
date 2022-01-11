package per.eicho.demo.leetcode.q701_800;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>783. Minimum Distance Between BST Nodes 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/minimum-distance-between-bst-nodes/">783. Minimum Distance Between BST Nodes</a>
 */
public final class Q783 {
    public int minDiffInBST(TreeNode root) {
        // The number of nodes in the tree is in the range [2, 100].
        // 0 <= Node.val <= 10^5
        List<Integer> values = new ArrayList<>(100);

        depthFirstInorderTraversal(values, root);

        int result = values.get(1) - values.get(0);
        for (int i = 2; i < values.size(); i++) {
            result = Math.min(result, values.get(i) - values.get(i - 1));
        }

        return result;
    }

    private void depthFirstInorderTraversal(final List<Integer> values, TreeNode root) {
        if (root == null) return;

        depthFirstInorderTraversal(values, root.left);
        values.add(root.val);
        depthFirstInorderTraversal(values, root.right);
    }
}
