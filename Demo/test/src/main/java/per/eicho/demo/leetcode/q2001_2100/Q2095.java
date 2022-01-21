package per.eicho.demo.leetcode.q2001_2100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>2095. Delete the Middle Node of a Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/">2095. Delete the Middle Node of a Linked List</a>
 */
public final class Q2095 {
    public ListNode deleteMiddle(ListNode head) {
        // 1. The number of nodes in the list is in the range [1, 10^5].
        // 2. 1 <= Node.val <= 10^5
        if (head == null || head.next == null) return null;


        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            fast = fast.next;
            if (fast.next == null) break;
            fast = fast.next;
            if (fast.next == null) break;

            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }
}
