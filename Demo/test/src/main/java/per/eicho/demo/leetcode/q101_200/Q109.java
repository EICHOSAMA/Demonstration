package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.ListNode;
import per.eicho.demo.leetcode.datastructure.TreeNode;

/**
 * <p>109. Convert Sorted List to Binary Search Tree 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/">109. Convert Sorted List to Binary Search Tree</a>
 */
public final class Q109 {
    public TreeNode sortedListToBST(ListNode head) {
        final ListNode cursor = new ListNode(0);
        cursor.next = head; 

        final int count = count(head);
        return build(0, count - 1, cursor);
    }

    private int count(ListNode head) {
        int result = 0;
        while (head != null) {
            result++;
            head = head.next;
        }
        return result;
    }

    private TreeNode build(int l, int r, final ListNode cursor) {
        if (l > r) return null;

        final TreeNode root = new TreeNode();
        final int mid = (l + r) / 2;

        // 1. left
        root.left = build(l, mid - 1, cursor);
        // 2. root
        root.val = cursor.next.val;
        moveCursor2Next(cursor);
        // 3. right
        root.right = build(mid + 1, r, cursor);
        return root;
    }

    private void moveCursor2Next(final ListNode cursor) {
        cursor.next = cursor.next.next;
    }
}
