package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>103. Binary Tree Zigzag Level Order Traversal 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/">103. Binary Tree Zigzag Level Order Traversal</a>
 */
final public class Q103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<TreeNode> currentLayer = Arrays.asList(root);
        boolean direction = true; // left 2 right: true, right 2 left: false
        while (currentLayer.size() > 0) {
            final List<Integer> l;

            if (direction) {
                l = currentLayer.stream()
                        .map(node -> node.val)
                        .collect(Collectors.toList());
            } else {
                l = currentLayer.stream()
                        .map(node -> node.val)
                        .sorted(Collections.reverseOrder())
                        .collect(Collectors.toList());
            }
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
            direction = !direction;
        }

        return result;
    }
}
