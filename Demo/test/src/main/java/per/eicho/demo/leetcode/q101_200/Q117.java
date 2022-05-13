package per.eicho.demo.leetcode.q101_200;

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
        Node cursor = root;
        final Node vituralHead = new Node(-101);
        Node tail = vituralHead; // vitural
        while (cursor != null) {

            while (cursor != null) {
                if (cursor.left != null) {
                    tail.next = cursor.left;
                    tail = tail.next;
                }

                if (cursor.right != null) {
                    tail.next = cursor.right;
                    tail = tail.next;
                }

                cursor = cursor.next;
            }

            // move to next layer
            cursor = vituralHead.next;

            // reset VH .
            vituralHead.next = null;
            tail = vituralHead;
        }

        return root;
    }
}
