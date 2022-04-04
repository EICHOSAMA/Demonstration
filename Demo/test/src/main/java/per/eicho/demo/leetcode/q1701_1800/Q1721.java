package per.eicho.demo.leetcode.q1701_1800;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>1721. Swapping Nodes in a Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/swapping-nodes-in-a-linked-list/">1721. Swapping Nodes in a Linked List</a>
 */
public final class Q1721 {
    
    public ListNode swapNodes(ListNode head, int k) {
        // 1. The number of nodes in the list is n.
        // 2. 1 <= k <= n <= 10^5
        // 3. 0 <= Node.val <= 100
        ListNode cursor = head;
        
        ListNode firstNode = head;
        
        
        int count = 0;
        while (count < k) {
            count++;
            cursor = cursor.next;
        }
        firstNode = cursor;

        ListNode lastNode = head;
        while (cursor.next != null) {
            cursor = cursor.next;
            lastNode = lastNode.next;
        }

        if (firstNode == lastNode) return head;

        firstNode.val ^= lastNode.val;
        lastNode.val ^= firstNode.val;
        firstNode.val ^= lastNode.val;

        return head;
    }

}
