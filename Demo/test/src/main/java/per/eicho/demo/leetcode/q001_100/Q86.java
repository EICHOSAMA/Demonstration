package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>86. Partition List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/partition-list/">86. Partition List</a>
 */
final public class Q86 {
    public ListNode partition(ListNode head, int x) {
        final ListNode lRoot = new ListNode(0);
        final ListNode rRoot = new ListNode(0);

        ListNode cL = lRoot; // current L
        ListNode cR = rRoot; // current R

        while (head != null) {
            if (head.val < x) {
                cL.next = head;
                cL = head;
            } else {
                // assert head.val >= x;
                cR.next = head;
                cR = head;
            }

            // move
            head = head.next;
        }

        // merge two List
        cL.next = rRoot.next;
        cR.next = null;
        
        return lRoot.next;
    }
}
