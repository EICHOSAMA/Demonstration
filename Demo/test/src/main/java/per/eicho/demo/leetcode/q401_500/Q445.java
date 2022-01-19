package per.eicho.demo.leetcode.q401_500;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>445. Add Two Numbers II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/add-two-numbers-ii/">445. Add Two Numbers II</a>
 */
public final class Q445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. The number of nodes in each linked list is in the range [1, 100].
        // 2. 0 <= Node.val <= 9
        // 3. It is guaranteed that the list represents a number that does not have leading zeros.

        l1 = reverseLinkedList(l1);
        l2 = reverseLinkedList(l2);

        ListNode newHead = new ListNode(-1);

        ListNode cursor1 = l1, cursor2 = l2;
        ListNode cursor = newHead;
        int carry = 0;
        while (cursor1 != null || cursor2 != null || carry != 0) {
            int result = carry;

            if (cursor1 != null) {
                result += cursor1.val;
                cursor1 = cursor1.next;
            }

            if (cursor2 != null) {
                result += cursor2.val;
                cursor2 = cursor2.next;
            }

            System.out.println(result);

            carry = result / 10;
            result = result % 10;

            cursor.next = new ListNode(result);
            cursor = cursor.next;
        }
        
        return reverseLinkedList(newHead.next);
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode tail = null;
        ListNode cursor = head;
        
        while (cursor != null) {
            final ListNode nextNode = cursor.next;

            cursor.next = tail;
            tail = cursor;
            cursor = nextNode;
        }

        return tail;
    }

    public static void main(String[] args) {
        Q445 q445 = new Q445();

        ListNode input1 = new ListNode(1);
        input1.next = new ListNode(2);
        input1.next.next = new ListNode(3);

        ListNode input2 = new ListNode(4);
        input2.next = new ListNode(5);
        input2.next.next = new ListNode(6);

        ListNode result = q445.addTwoNumbers(input1, input2);

        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }

    }
}
