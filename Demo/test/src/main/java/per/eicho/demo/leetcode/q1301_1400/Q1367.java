package per.eicho.demo.leetcode.q1301_1400;

import per.eicho.demo.leetcode.datastructure.ListNode;
import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>1367. Linked List in Binary Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-in-binary-tree/">1367. Linked List in Binary Tree</a>
 */
public final class Q1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        // 1. The number of nodes in the tree will be in the range [1, 2500].
        // 2. The number of nodes in the list will be in the range [1, 100].
        // 3. 1 <= Node.val <= 100 for each node in the linked list and binary tree.

        return isSubPath(head, root, false);
    }

    private boolean isSubPath(ListNode head, TreeNode root, boolean isMatching) {
        if (head == null) return true;
        if (root == null) return false;
        if (isMatching) {
            if (head.val != root.val) return false;
            return isSubPath(head.next, root.left, true) || isSubPath(head.next, root.right, true);
        }

        boolean result = false;
        if (head.val == root.val) {
            result = isSubPath(head.next, root.left, true) || isSubPath(head.next, root.right, true);
        }

        return result || isSubPath(head, root.left, false) || isSubPath(head, root.right, false);
    }
}
