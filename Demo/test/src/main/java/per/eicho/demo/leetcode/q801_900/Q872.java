package per.eicho.demo.leetcode.q801_900;

import java.util.ArrayList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>872. Leaf-Similar Trees 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/cousins-in-binary-tree/">
 *   872. Leaf-Similar Trees</a>
 */
public final class Q872 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        final List<TreeNode> leafNodes1 = new ArrayList<>();
        inOrderTraversal(root1, leafNodes1);

        final List<TreeNode> leafNodes2 = new ArrayList<>();
        inOrderTraversal(root2, leafNodes2);        

        if (leafNodes1.size() != leafNodes2.size()) return false;

        for (int i = 0; i < leafNodes1.size(); i++) {
            if (leafNodes1.get(i).val != leafNodes2.get(i).val) return false;
        }
        return true;
    }

    private void inOrderTraversal(TreeNode root, List<TreeNode> leafNodes) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            leafNodes.add(root);
            return;
        }

        inOrderTraversal(root.left, leafNodes);
        inOrderTraversal(root.right, leafNodes);
    }
}
