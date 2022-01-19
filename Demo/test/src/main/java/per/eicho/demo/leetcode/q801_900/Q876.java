package per.eicho.demo.leetcode.q801_900;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>876. Middle of the Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/middle-of-the-linked-list/">876. Middle of the Linked List</a>
 */
public final class Q876 {
    public ListNode middleNode(ListNode head) {
        // 1. The number of nodes in the list is in the range [1, 100].
        // 2.1 <= Node.val <= 100
        ListNode fastCursor = head;
        ListNode slowCursor = head;

        while (fastCursor != null && fastCursor.next != null) {
            slowCursor = slowCursor.next;
            fastCursor = fastCursor.next.next;
        }
        return slowCursor;
    }
}
