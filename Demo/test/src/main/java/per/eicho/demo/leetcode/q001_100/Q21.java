package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>21. Merge Two Sorted Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-two-sorted-lists/">21. Merge Two Sorted Lists</a>
 */
public final class Q21 {
        /**
     * Merge two sorted linked lists and return it as a new list. </br>
     * The new list should be made by splicing together the nodes of the first two lists.
     *
     * @param l1 sorted, asc
     * @param l2 sorted, asc
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

//        assert  l1 != null && l2 != null
        ListNode result = new ListNode(0); // temp
        ListNode cursor = result;

        do {
            if (l1.val <= l2.val) {
                cursor.next = l1; // set next.
                cursor = l1; // move to next.
                l1 = l1.next;
            } else {
                // l1.val > l2.val
                cursor.next = l2;
                cursor = l2;
                l2 = l2.next;
            }
        } while (l1 != null && l2 != null);

        if (l1 == null)
            cursor.next = l2;
        else
            cursor.next = l1;

        return result.next;
    }
}
