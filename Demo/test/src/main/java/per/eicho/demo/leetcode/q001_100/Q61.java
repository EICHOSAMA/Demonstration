package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>61. Rotate List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rotate-list/">61. Rotate List</a>
 */
public final class Q61 {
    ListNode lastNode;

    public ListNode rotateRight(ListNode head, int k) {
        // 1. The number of nodes in the list is in the range [0, 500].
        // 2. -100 <= Node.val <= 100
        // 3. 0 <= k <= 2 * 10^9        
        if (head == null) return head;
        final int size = count(head);
        k %= size;
        if (k == 0) return head;

        ListNode tail = head;
        int prefixCount = size - k - 1;
        while (prefixCount > 0) {
            prefixCount--;
            tail = tail.next;
        }
        ListNode newHead = tail.next;
        tail.next = null;
        lastNode.next = head;
        return newHead;
    }

    private int count(ListNode head) {
        // head != null;
        ListNode cursor = head;
        int len = 1;
        while (cursor.next != null) {
            len++;
            cursor = cursor.next;
        }
        lastNode = cursor;
        return len;
    }
}
