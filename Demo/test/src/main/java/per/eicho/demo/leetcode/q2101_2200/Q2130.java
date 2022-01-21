package per.eicho.demo.leetcode.q2101_2200;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>2130. Maximum Twin Sum of a Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/">2130. Maximum Twin Sum of a Linked List</a>
 */
public final class Q2130 {

    public int pairSum(ListNode head) {
        int result = 0;
        // 1. The number of nodes in the list is an even integer in the range [2, 10^5].
        // 2. 1 <= Node.val <= 10^5
        final Stack<ListNode> stack = new Stack<>();

        final ListNode vituralHead = new ListNode(-1);
        vituralHead.next = head;

        ListNode fast = vituralHead;
        ListNode slow = vituralHead;

        while (fast.next != null) {
            // assert fast.next.next != null : "The length of given linked list is an even integer.";
            fast = fast.next.next;
            slow = slow.next;
        }

        while (slow.next != null) {
            slow = slow.next;
            stack.add(slow);
        }

        ListNode cursor = vituralHead;
        while (!stack.isEmpty()) {
            cursor = cursor.next;
            final ListNode node = stack.pop();
            result = Math.max(result, cursor.val + node.val);
        }

        return result;
    }
}
