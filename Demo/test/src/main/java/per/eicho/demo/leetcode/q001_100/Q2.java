package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * 2. Add Two Numbers 的题解代码
 * 
 * 
 * <pre>
 *  You are given two non-empty linked lists representing two non-negative integers. 
 *  The digits are stored in reverse order, and each of their nodes contains a single digit. 
 *  Add the two numbers and return the sum as a linked list.
 *  
 *  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * </pre>
 * @see <a href="https://leetcode.com/problems/add-two-numbers/">2. Add Two Numbers</a>
 */
final public class Q2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        assert l1 != null;
        assert l2 != null;

        ListNode result = l1;
        int carryDigit = 0;
        int caseNo = 0;
        ListNode temp0Node = new ListNode(0); // for saving memory.
        while(true) {
            l1.val += l2.val + carryDigit;
            carryDigit = l1.val / 10;
            l1.val = l1.val % 10;

            caseNo = 0;
            if (null == l1.next) caseNo += 1;
            if (null == l2.next) caseNo += 2;

            if (caseNo == 0) {
                l1 = l1.next;
                l2 = l2.next;
                continue;
            }

            if (caseNo == 3) {
                if (carryDigit == 0)
                    break; // stop
                l1.next = new ListNode(carryDigit);
                break;
            }


            if (caseNo == 1) {
                l1.next = l2.next;
                l2.next = null;
            } else {
                //doNothing.
            }

            if (0 == carryDigit)
                break;

            l1 = l1.next;
            l2 = temp0Node;
        }

        return result;
    }
}
