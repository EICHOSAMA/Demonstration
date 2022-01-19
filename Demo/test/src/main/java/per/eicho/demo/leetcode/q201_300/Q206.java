package per.eicho.demo.leetcode.q201_300;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>206. Reverse Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/">206. Reverse Linked List</a>
 */
public final class Q206 {
    public ListNode reverseList(ListNode head) {
        ListNode cursor = head;
        ListNode tailPointer = null;
        ListNode nextNode;
        while (cursor != null) {
            nextNode = cursor.next; // record.
            cursor.next = tailPointer; // redirect next to root'next as new head.
            tailPointer = cursor; // record new head.
            cursor = nextNode; // move
        }

        return tailPointer;        
    }

    /**
     * recursive version
     */
    public ListNode reverseList2(ListNode head) {
        // 1. The number of nodes in the list is the range [0, 5000].
        // 2.-5000 <= Node.val <= 5000
        if (head == null) return null;
        
        final ListNode nextNode = head.next;
        head.next = null;
        if (nextNode == null) return head;

        final ListNode reversedListHead = reverseList(nextNode);
        nextNode.next = head;
        return reversedListHead;
    }
}
