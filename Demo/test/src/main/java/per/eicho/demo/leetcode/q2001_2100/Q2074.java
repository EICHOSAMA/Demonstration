package per.eicho.demo.leetcode.q2001_2100;

import java.util.Stack;

import per.eicho.demo.leetcode.datastructure.ListNode;
import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>2074. Reverse Nodes in Even Length Groups 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reverse-nodes-in-even-length-groups/">2074. Reverse Nodes in Even Length Groups</a>
 */
public final class Q2074 {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        // 1. The number of nodes in the list is in the range [1, 10^5].
        // 2. 0 <= Node.val <= 10^5
        int groupIndex = 0; // 1-based
        final Stack<ListNode> stack = new Stack<>();
        
        final ListNode vituralHead = new ListNode(-1);
        vituralHead.next = head;
        
        ListNode previousTail = vituralHead; // tail of previous group
        while (previousTail.next != null) {
            groupIndex++; // groupIndex th group.

            int remain = groupIndex;
            ListNode currentTail = previousTail.next;

            while (remain > 1 && currentTail.next != null) {
                stack.add(currentTail);
                currentTail = currentTail.next;
                remain--;
            }
            // assert currentTail != null;
            stack.add(currentTail);

            if (stack.size() % 2 != 0) {
                // Odd Length Group
                stack.clear();
                previousTail = currentTail;
            } else {
                // Even Length Group
                final ListNode nextNode = currentTail.next;
                while (!stack.isEmpty()) {
                    previousTail.next = stack.pop();
                    previousTail = previousTail.next;
                }
                previousTail.next = nextNode;
            }
        }

        return vituralHead.next;
    }

    public static void main(String[] args) {
        
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        
        Q2074 q2074 = new Q2074();
        OutputUtils.println(head);
        ListNode result = q2074.reverseEvenLengthGroups(head);
        OutputUtils.println(result);
    }
}
