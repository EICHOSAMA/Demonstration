package per.eicho.demo.leetcode.q701_800;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>725. Split Linked List in Parts 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/split-linked-list-in-parts/">725. Split Linked List in Parts</a>
 */
public final class Q725 {
    public ListNode[] splitListToParts(ListNode head, int k) {
        final ListNode[] result = new ListNode[k];
        final int[] lens = new int[k];
        final int count = count(head);
        
        int remainNode = count;
        int remainParts = k;
        for (int i = 0; i < k; i++) {
            lens[i] = (remainNode / remainParts) + (remainNode % remainParts > 0 ? 1 : 0);
            remainParts--;
            remainNode -= lens[i];
        }

        ListNode cursor = head;
        ListNode temp;
        for (int i = 0; i < k; i++) {
            int len = lens[i];

            if (cursor == null) break;
            result[i] = cursor;

            while (len != 1) {
                len--;
                cursor = cursor.next;
            }

            temp = cursor.next;
            cursor.next = null;
            cursor = temp;
        }

        return result;
    }

    private int count(ListNode head) {
        int result = 0;
        while (head != null) {
            result++;
            head = head.next;
        }
        return result;
    }
}
