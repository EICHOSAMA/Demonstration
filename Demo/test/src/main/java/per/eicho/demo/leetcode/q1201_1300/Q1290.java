package per.eicho.demo.leetcode.q1201_1300;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>1290. Convert Binary Number in a Linked List to Integer 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/">
 *   1290. Convert Binary Number in a Linked List to Integer</a>
 */
public final class Q1290 {
    public int getDecimalValue(ListNode head) {
        // The Linked List is not empty.
        // Number of nodes will not exceed 30.
        // Each node's value is either 0 or 1.
        int result = 0;
        
        ListNode cursor = head;
        while (cursor != null) {
            result = result * 2 + cursor.val;
            cursor = cursor.next;
        }
        return result;   
    }
}
