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
        Queue<Node> currrentStack = new LinkedList<>();
        Queue<Node> alternateStack = new LinkedList<>();

        currrentStack.add(root);

        while (currrentStack.peek() != null) {
            
            Node node = currrentStack.poll();
            while (node != null) {
                if (node.left != null) alternateStack.add(node.left);
                if (node.right != null) alternateStack.add(node.right);

                final Node right = currrentStack.poll();
                node.next = right;
                node = right;
            }

            Queue<Node> temp = alternateStack;
            alternateStack = currrentStack;
            currrentStack = temp;
        }
        return root;
    }
}
