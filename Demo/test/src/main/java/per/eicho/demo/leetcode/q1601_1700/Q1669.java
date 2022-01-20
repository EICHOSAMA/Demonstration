package per.eicho.demo.leetcode.q1601_1700;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>1669. Merge In Between Linked Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-in-between-linked-lists/">
 *   1669. Merge In Between Linked Lists</a>
 */
public final class Q1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 1. 3 <= list1.length <= 10^4
        // 2. 1 <= a <= b < list1.length - 1
        // 3. 1 <= list2.length <= 10^4
        final ListNode vituralHead = new ListNode(0);
        vituralHead.next = list1;

        ListNode cursor = vituralHead;
        int index = 0;

        ListNode aNode = null;
        ListNode bNode = null;
        while (cursor != null) {

            if (index == a) {
                aNode = cursor;
            }

            if (index == b + 1) {
                bNode = cursor;
                break;
            }

            index++;
            cursor = cursor.next;
        }

        ListNode tailOfList2 = list2;
        while (tailOfList2.next != null) {
            tailOfList2 = tailOfList2.next;
        }

        aNode.next = list2;
        tailOfList2.next = bNode.next;
        return vituralHead.next;
    }
}
