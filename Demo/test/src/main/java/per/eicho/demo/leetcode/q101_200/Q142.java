package per.eicho.demo.leetcode.q101_200;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>142. Linked List Cycle II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/linked-list-cycle-ii/">142. Linked List Cycle II</a>
 */
public final class Q142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        
        ListNode p1 = head;
        ListNode p2 = head.next;

        while (p1 != p2) {
            if (p2 == null) return null;
            
            // move
            p1 = p1.next;
            if (p2.next == null) return null;
            p2 = p2.next.next;
        }

        p1 = p1.next;
        ListNode p3 = head;        
        while (p1 != p3) {
            p1 = p1.next; 
            p3 = p3.next;
        }
        
        return p3;
    }

    public static void main(String[] args) {
        Q142 q142 = new Q142();
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        System.out.println(q142.detectCycle(head).val);
    }
}
