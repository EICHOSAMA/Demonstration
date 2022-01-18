package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>148. Sort List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/sort-list/">148. Sort List</a>
 */
public final class Q148 {
    public ListNode sortList(ListNode head) {
        /**
         * 1. The number of nodes in the list is in the range [0, 5 * 10^4].
         * 2. -10^5 <= Node.val <= 10^5
         */
        if (head == null) return null;
        return sortList(head, count(head));
    }

    private ListNode sortList(final ListNode node, final int count) {
        if (count == 1) return node;
        
        final int half = count / 2;
        final int countLeft = half;
        final int countRight = count - half;

        ListNode right = nodeAfter(node, countLeft);
        ListNode rN = nodeAfter(right, countRight);

        // Sort sub list
        ListNode sortedLeft = sortList(node, countLeft);
        ListNode sortedRight = sortList(right, countRight);

        final ListNode tempRoot = new ListNode(-1);

        // Merge sorted linked list
        ListNode cursor = tempRoot;
        ListNode cursorL = sortedLeft, cursorR = sortedRight;

        int remainLeft = countLeft;
        int remainRight = countRight;
        while (remainLeft > 0 || remainRight > 0) {

            if (remainLeft == 0) {
                cursor.next = cursorR;
                cursor = nodeAfter(cursorR, remainRight - 1);
                break;
            } else if (remainRight == 0) {
                cursor.next = cursorL;
                cursor = nodeAfter(cursorL, remainLeft - 1);
                break;
            }

            if (cursorL.val <= cursorR.val) {
                cursor.next = cursorL;
                cursor = cursor.next;
                remainLeft--;
                cursorL = cursorL.next;
            } else {
                cursor.next = cursorR;
                cursor = cursor.next;
                remainRight--;
                cursorR = cursorR.next;
            }
        }

        // reconnect
        cursor.next = rN;
        return tempRoot.next;
    }

    private ListNode nodeAfter(ListNode node, int count) {
        while (count > 0) {
            node = node.next;
            count--;
        }
        return node;
    }

    private int count(ListNode node) {
        int result = 0;
        while (node != null) {
            result++;
            node = node.next;
        }
        return result;
    }
}
