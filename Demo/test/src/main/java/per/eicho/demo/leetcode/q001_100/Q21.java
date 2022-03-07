package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>21. Merge Two Sorted Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-two-sorted-lists/">21. Merge Two Sorted Lists</a>
 */
public final class Q21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 1. The number of nodes in both lists is in the range [0, 50].
        // 2. -100 <= Node.val <= 100
        // 3. Both list1 and list2 are sorted in non-decreasing order.
        final ListNode dummyHead = new ListNode(0); // dummy Head
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail = (tail.next = l1);
                l1 = l1.next;
            } else {
                tail = (tail.next = l2);
                l2 = l2.next;
            }
        }
        tail.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }
}
