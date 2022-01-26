package per.eicho.demo.leetcode.q001_100;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>25. Reverse Nodes in k-Group 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/">25. Reverse Nodes in k-Group</a>
 */
public final class Q25 {
    public ListNode reverseKGroup2(ListNode head, int k) {
        // 1. The number of nodes in the list is n.
        // 2. 1 <= k <= n <= 5000
        // 3. 0 <= Node.val <= 1000
        if (k == 1) return head;

        final ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode p = dummy;
        Stack<ListNode> stack = new Stack<>();

        while (p.next != null) {
            ListNode cursor = p.next;

            while (stack.size() < k && cursor != null) {
                stack.add(cursor);
                cursor = cursor.next;
            }

            if (stack.size() != k) break;

            while (!stack.isEmpty()) {
                p.next = stack.pop();
                p = p.next;
            }
            p.next = cursor;
        }

        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode result = new ListNode(0); // temp
        result.next = head;
        // If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
        // k is a positive integer and is less than or equal to the length of the linked list.

        ListNode cursor = head; // point to unprocessed data.
        ListNode current;
        final ListNode groupHead = new ListNode(0); // temp.
        ListNode groupCursor = groupHead; // start of current group.
        int groupCount = 0;
        ListNode lastGroupTail = result;
        while (cursor != null) {
            current = cursor; // record first unprocessed node as current.
            cursor = cursor.next; //move unprocessed data cursor to next.

            groupCursor.next = current; // record next.
            groupCount++; //update count of nodes in the group.
            groupCursor = current;// move group cursor
            if (groupCount == k) {
                // do reverse with those information {lastGroupTail, groupHead, groupCursor, groupCount}
                ListNode newGroupHead = groupHead.next;
                ListNode newGroupTail = newGroupHead;
                ListNode newGroupNode;
                while (--groupCount != 0) {
                    // pop new node.
                    newGroupNode = newGroupTail.next;
                    newGroupTail.next = newGroupNode.next;
                    // move new node to head.
                    newGroupNode.next = newGroupHead;
                    newGroupHead = newGroupNode;
                }
                lastGroupTail.next = newGroupHead; // connect last group with new group.
                newGroupTail.next = cursor; // connet new group with unprocessed node.
                // update last group tail.
                lastGroupTail = newGroupTail;

                // clear group status.
                groupHead.next = null;
                groupCursor = groupHead;
                //groupCount = 0;
            }
        }

        return result.next;
    }
}
