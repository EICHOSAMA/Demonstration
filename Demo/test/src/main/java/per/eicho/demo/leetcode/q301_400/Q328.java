package per.eicho.demo.leetcode.q301_400;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>328. Odd Even Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/odd-even-linked-list/">328. Odd Even Linked List</a>
 */
public final class Q328 {
    public ListNode oddEvenList(ListNode head) {
        // 1. n == number of nodes in the linked list
        // 2.0 <= n <= 10^4
        // 3. -10^6 <= Node.val <= 10^6
        if (head == null || head.next == null) return head;
        
        ListNode oddHead = head;
        ListNode evenHead = head.next;


        ListNode cursor = evenHead.next;
        oddHead.next = null;
        evenHead.next = null;

        ListNode oddCursor = oddHead;
        ListNode evenCursor = evenHead;        
        while (cursor != null) {
            ListNode oddNode = cursor; // assert not null
            ListNode evenNode = cursor.next; // may be null

            oddCursor.next = oddNode;
            oddCursor = oddNode;

            evenCursor.next = evenNode;
            evenCursor = evenNode;

            if (evenCursor == null) break;
            cursor = evenCursor.next;

            oddNode.next = null;
            evenCursor.next = null;
        }

        oddCursor.next = evenHead;

        return oddHead;
    }
}
