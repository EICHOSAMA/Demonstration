package per.eicho.demo.leetcode.q001_100;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>99. Recover Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/recover-binary-search-tree/">99. Recover Binary Search Tree</a>
 */
public final class Q99 {
    public void recoverTree(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [2, 1000].
        // 2. -2^31 <= Node.val <= 2^31 - 1
        // Recover the tree without changing its structure.
        final List<TreeNode> nodes = new ArrayList<>(); 
        inOrderTraversal(root, nodes);
        final TreeNode[] swapTarget = findSwapTarget(nodes);

        final int temp = swapTarget[0].val;
        swapTarget[0].val = swapTarget[1].val;
        swapTarget[1].val = temp;
    }

    public TreeNode[] findSwapTarget(List<TreeNode> nodes) {
        final int n = nodes.size();
        int index1 = -1, index2 = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nodes.get(i + 1).val > nodes.get(i).val) continue;
            index2 = i + 1;
            if (index1 != -1) break;
            index1 = i;
        }
        return new TreeNode[]{nodes.get(index1), nodes.get(index2)};
    }

    private void inOrderTraversal(TreeNode node, List<TreeNode> output) {
        if (null == node) return;
        inOrderTraversal(node.left, output);
        output.add(node);
        inOrderTraversal(node.right, output);        
    }
}
