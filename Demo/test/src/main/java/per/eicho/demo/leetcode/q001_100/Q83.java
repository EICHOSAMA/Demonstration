package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>83. Remove Duplicates from Sorted List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list/">
 *  83. Remove Duplicates from Sorted List</a>
 */
public final class Q83 {
    public ListNode deleteDuplicates(ListNode head) {
        // 1. The number of nodes in the list is in the range [0, 300].
        // 2. -100 <= Node.val <= 100
        // 3. The list is guaranteed to be sorted in ascending order.        
        if (head == null) return null;
        ListNode tail = head;
        ListNode cursor = head.next;
        tail.next = null;
        while (cursor != null) {
            if (cursor.val != tail.val) {
                // add to list.
                tail.next = cursor; // add cursor to tail.
                tail = cursor; // move tail.
                cursor = cursor.next; // move cursor to next node.
                tail.next = null; // cut connection.
            } else {
                cursor = cursor.next; // move cursor to next node.
            }
        }
        return head;
    }
}
