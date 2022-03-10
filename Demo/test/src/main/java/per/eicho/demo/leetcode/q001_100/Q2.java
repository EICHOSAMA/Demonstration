package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>2. Add Two Numbers 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-two-numbers/">2. Add Two Numbers</a>
 */
final public class Q2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. The number of nodes in each linked list is in the range [1, 100].
        // 2. 0 <= Node.val <= 9
        // 3. It is guaranteed that the list represents a number that does not have leading zeros.

        final ListNode result = new ListNode(0); // dummy head.

        int carryDigit = 0;
        ListNode tail = result;
        while(l1 != null || l2 != null || carryDigit != 0) {
            int val = calculate(l1, l2, carryDigit);
            carryDigit = val / 10;
            val %= 10;

            tail.next = new ListNode(val);
            tail = tail.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return result.next;
    }

    private int calculate(ListNode l1, ListNode l2, int carryDigit) {
        return (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carryDigit;
    }
}
