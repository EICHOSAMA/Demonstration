package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>147. Insertion Sort List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/insertion-sort-list/">147. Insertion Sort List</a>
 */
public final class Q147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode newHead = new ListNode(-5001);
        newHead.next = head;
        // 1. The number of nodes in the list is in the range [1, 5000].
        // 2. -5000 <= Node.val <= 5000
        ListNode remainHead = head.next;
        head.next = null;

        while (remainHead != null) {
            final ListNode node = remainHead;

            remainHead = node.next;
            node.next = null;

            insert(newHead, node);
        }
        return newHead.next;
    }

    private void insert(ListNode head, ListNode node) {

        ListNode cursor = head;
        while (cursor.next != null) {
            if (cursor.next.val < node.val) {
                cursor = cursor.next;
            } else {
                //assert cursor.val < node.val && node.val <= cursor.next.val;
                break;
            } 
        }

        node.next = cursor.next;
        cursor.next = node;
    }
}
