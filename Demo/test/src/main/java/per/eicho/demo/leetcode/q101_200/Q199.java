package per.eicho.demo.leetcode.q101_200;

import java.util.LinkedList;
import java.util.List;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>199. Binary Tree Right Side View 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-right-side-view/">199. Binary Tree Right Side View</a>
 */
public final class Q199 {
    public List<Integer> rightSideView(TreeNode root) {
        // The number of nodes in the tree is in the range [0, 100].
        // -100 <= Node.val <= 100
        final List<Integer> result = new LinkedList<>();

        if (root == null) return result;

        LinkedList<TreeNode> currentLayer = new LinkedList<>();
        LinkedList<TreeNode> nextLayer = new LinkedList<>();

        currentLayer.add(root);
        while (currentLayer.size() != 0) {
            
            TreeNode node = null;
            while (currentLayer.size() != 0) {
                node = currentLayer.pollFirst();

                if (node.left != null) nextLayer.add(node.left);
                if (node.right != null) nextLayer.add(node.right);
            }

            // add to result list.
            result.add(node.val);

            // swap
            LinkedList<TreeNode> temp = currentLayer;
            currentLayer = nextLayer;
            nextLayer = temp;
        }
        
        return result;
    }
}
