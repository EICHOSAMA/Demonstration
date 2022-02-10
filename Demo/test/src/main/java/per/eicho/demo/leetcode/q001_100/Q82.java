package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>82. Remove Duplicates from Sorted List II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/">82. Remove Duplicates from Sorted List II</a>
 */
public final class Q82 {
    public ListNode deleteDuplicates(ListNode head) {
        // 1. The number of nodes in the list is in the range [0, 300].
        // 2. -100 <= Node.val <= 100
        // 3. The list is guaranteed to be sorted in ascending order.        
        final ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;  

        ListNode tail = dummyHead;
        ListNode cursor = head;

        while (cursor != null) {
            if (cursor.next == null) break;
            if (cursor.next.val != cursor.val) {
                tail = cursor;
                cursor = cursor.next;
                continue;
            }

            final int duplicateNum = cursor.val;
            while (cursor != null && cursor.val == duplicateNum) cursor = cursor.next;
            tail.next = cursor;
        }

        return dummyHead.next;
    }
}
