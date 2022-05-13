package per.eicho.demo.leetcode.q101_200;

import java.util.LinkedList;
import java.util.Queue;

import per.eicho.demo.leetcode.datastructure.Node;

/**
 * <p>117. Populating Next Right Pointers in Each Node II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/">117. Populating Next Right Pointers in Each Node II</a>
 */
public final class Q117 {
    public Node connect(Node root) {
        // 1. The number of nodes in the tree is in the range [0, 6000].
        // 2. -100 <= Node.val <= 100
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 1; i < size; i++) {
                final Node node = queue.poll();
                addSubNode2Queue(node, queue);
                node.next = queue.peek();
            }
            addSubNode2Queue(queue.poll(), queue);
        }
        return root;
    }

    private void addSubNode2Queue(Node node, Queue<Node> queue) {
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
