package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>19. Remove Nth Node From End of List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/">19. Remove Nth Node From End of List</a>
 */
public final class Q19 {
    private ListNode target;
    private ListNode cursor;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 1. find target node to remove from the linked list.
         */
        int count = 1;
        cursor = head;
        target = head;
        int distanceFromTempToCursor = 0;
        while (cursor.next != null) {
            cursor = cursor.next;
            count++;

            if (distanceFromTempToCursor < n) {
                distanceFromTempToCursor++; // update distance.
            } else {
                target = target.next; // move target to keep the distance = n.
            }
        }

        if (n == count) {
            return head.next;
        }

        target.next = target.next.next; // remove the node.
        return head;
    }
}
