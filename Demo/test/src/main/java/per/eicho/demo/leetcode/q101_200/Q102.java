package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>102. Binary Tree Level Order Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">102. Binary Tree Level Order Traversal</a>
 */
final public class Q102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            final int count = queue.size();
            final List<Integer> layer = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                final TreeNode node = queue.poll();
                layer.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right); 
            }
            result.add(layer);
        }
        return result;        
    }
}
