package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>102. Binary Tree Level Order Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">102. Binary Tree Level Order Traversal</a>
 */
final public class Q102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<TreeNode> currentLayer = Arrays.asList(root);
        while (currentLayer.size() > 0) {
            final List<Integer> l = currentLayer.stream()
                .map(node -> node.val)
                .collect(Collectors.toList());

            result.add(l);

            final List<TreeNode> nextLayer = new ArrayList<>();
            currentLayer.forEach(node -> {
                if (node.left != null) {
                    nextLayer.add(node.left);
                }

                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            });

            currentLayer = nextLayer;
        }

        return result;
    }
}
