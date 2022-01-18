package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>141. Linked List Cycle 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/">141. Linked List Cycle</a>
 */
public final class Q141 {
    public boolean hasCycle(ListNode head) {
        if (null == head) return false;
        ListNode cursor = head;
        while (cursor.next != null && cursor.next != cursor) {
            head = cursor.next; // move head
            cursor.next = cursor; // cut edge
            cursor = head; // move cursor to next
        }
        return cursor.next == cursor;
    }
}
