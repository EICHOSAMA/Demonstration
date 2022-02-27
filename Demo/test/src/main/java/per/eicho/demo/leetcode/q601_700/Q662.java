package per.eicho.demo.leetcode.q601_700;

import java.util.Deque;
import java.util.LinkedList;

import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>662. Maximum Width of Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-width-of-binary-tree/">
 *   662. Maximum Width of Binary Tree</a>
 */
public final class Q662 {
    public int widthOfBinaryTree(TreeNode root) {
        // 1. The number of nodes in the tree is in the range [1, 3000].
        // 2. -100 <= Node.val <= 100        
        Deque<TreeNode> nodes = new LinkedList<>();
        Deque<TreeNode> nodes2 = new LinkedList<>();
        Deque<Integer> indexes = new LinkedList<>();
        Deque<Integer> indexes2 = new LinkedList<>();
        
        nodes.add(root);
        indexes.add(1);

        int result = 0;
        while (!nodes.isEmpty()) {
            result = Math.max(result, indexes.peekLast() - indexes.peekFirst() + 1);

            while (!nodes.isEmpty()) {
                final TreeNode node = nodes.poll();
                final int index = indexes.poll();

                if (node.left != null) {
                    nodes2.add(node.left);
                    indexes2.add(index * 2);
                }

                if (node.right != null) {
                    nodes2.add(node.right);
                    indexes2.add(index * 2 + 1);
                }
            }

            final Deque<TreeNode> tempNodes = nodes;
            final Deque<Integer> tempIndexes = indexes;
            nodes = nodes2;
            indexes = indexes2;

            nodes2 = tempNodes;
            indexes2 = tempIndexes;
        }

        return result;
    }
}
