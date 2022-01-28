package per.eicho.demo.leetcode.q901_1000;

import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>993. Cousins in Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/cousins-in-binary-tree/">
 *   993. Cousins in Binary Tree</a>
 */
public final class Q993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        // 1. The number of nodes in the tree is in the range [2, 100].
        // 2. 1 <= Node.val <= 100
        // 3. Each node has a unique value.
        // 4. x != y
        // 5. x and y are exist in the tree.

        // WFS
        Queue<TreeNode> layer = new LinkedList<>();
        layer.add(root);
        Queue<TreeNode> nextLayer = new LinkedList<>();

        int mark = 0b00;
        while (mark == 0b00) {

            while (!layer.isEmpty()) {
                final TreeNode node = layer.poll();
                if (node.val == x) mark |= 0b01;
                if (node.val == y) mark |= 0b10;

                if (node.left != null) nextLayer.add(node.left);
                if (node.right != null) nextLayer.add(node.right);

                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y) return false;
                    if (node.left.val == y && node.right.val == x) return false;
                }
            }

            Queue<TreeNode> temp = layer;
            layer = nextLayer;
            nextLayer = temp;
        }

        return mark == 0b11;
    }
}
