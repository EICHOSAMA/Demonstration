package per.eicho.demo.leetcode.q1101_1200;

import java.util.HashMap;
import java.util.Map;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>1171. Remove Zero Sum Consecutive Nodes from Linked List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/">1171. Remove Zero Sum Consecutive Nodes from Linked List</a>
 */
public final class Q1171 {
    public ListNode removeZeroSumSublists(ListNode head) {
        // 1. The given linked list will contain between 1 and 1000 nodes.
        // 2. Each node in the linked list has -1000 <= node.val <= 1000.
        final Map<Integer, ListNode> map = new HashMap<>(); // Key: Sum , Val: node
        final ListNode vituralHead = new ListNode(0);
        vituralHead.next = head;
        map.put(0, vituralHead);

        int sum = 0;
        
        ListNode cursor = head;
        while (cursor != null) {
            sum += cursor.val;
            final Integer iSum = sum;

            if (!map.containsKey(iSum)) {
                map.put(iSum, cursor);
            } else {
                final ListNode node = map.get(iSum);
                
                ListNode delCursor = node.next;
                node.next = cursor.next;

                int delSum = sum;
                while (delCursor != cursor) {
                    delSum += delCursor.val;
                    map.remove(delSum);
                    delCursor = delCursor.next;
                }
            }

            cursor = cursor.next;
        }
        return vituralHead.next;
    }

    public static void main(String[] args) {
        Q1171 q1171 = new Q1171();

        
        ListNode head = new ListNode(1);
        ListNode tail = head;
        tail.next = new ListNode(3);
        tail = tail.next;
        tail.next = new ListNode(2);
        tail = tail.next;
        tail.next = new ListNode(-3);
        tail = tail.next;
        tail.next = new ListNode(-2);
        tail = tail.next;
        tail.next = new ListNode(5);
        tail = tail.next;
        tail.next = new ListNode(5);        
        tail = tail.next;
        tail.next = new ListNode(-5);
        tail = tail.next;
        tail.next = new ListNode(1);
        tail = tail.next;
        

        ListNode result = q1171.removeZeroSumSublists(head);

        while (result != null) {
            System.out.print(result.val);
            System.out.print(",");
            result = result.next;
        }
    }
}
