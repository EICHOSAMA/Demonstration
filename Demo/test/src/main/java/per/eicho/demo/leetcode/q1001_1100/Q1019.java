package per.eicho.demo.leetcode.q1001_1100;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>1019. Next Greater Node In Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/next-greater-node-in-linked-list/">1019. Next Greater Node In Linked List</a>
 */
public final class Q1019 {
    public int[] nextLargerNodes(ListNode head) {
        // 1. The number of nodes in the list is n.
        // 2. 1 <= n <= 10^4
        // 3. 1 <= Node.val <= 10^9
        // key word: Monotone Stack
        Stack<ListNode> stack = new Stack<>();
        stack.add(head);

        // 1. main process
        int count = 1;
        ListNode cursor = head.next;
        while (cursor != null) {
            count++;

            while (!stack.isEmpty() && stack.peek().val < cursor.val) {
                ListNode top = stack.pop();
                top.val = cursor.val; // change node val to save the next greater node information.
            }

            stack.add(cursor);
            cursor = cursor.next;
        }

        while (!stack.isEmpty()) {
            stack.pop().val = 0;
        }

        // 2. convert to result data structure.
        final int[] result = new int[count];
        cursor = head;
        int i = 0;
        while (cursor != null) {
            result[i++] = cursor.val;
            cursor = cursor.next; 
        }

        return result;
    }
}
