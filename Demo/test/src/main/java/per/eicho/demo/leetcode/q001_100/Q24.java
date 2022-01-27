package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>24. Swap Nodes in Pairs 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/swap-nodes-in-pairs/">24. Swap Nodes in Pairs</a>
 */
public final class Q24 {
    public ListNode swapPairs(ListNode head) {
        // 1. The number of nodes in the list is in the range [0, 100].
        // 2. 0 <= Node.val <= 100
        final ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode tail = dummy;
        ListNode node1, node2;
        while (tail.next != null) {
            node1 = tail.next;
            node2 = node1.next;

            if (node2 == null) {
                tail = node1;
                continue;
            }

            tail.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            tail = node1;
        }

        return dummy.next;   
    }
}
