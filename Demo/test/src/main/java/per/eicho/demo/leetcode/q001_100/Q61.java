package per.eicho.demo.leetcode.q001_100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>61. Rotate List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/rotate-list/">61. Rotate List</a>
 */
public final class Q61 {
    public ListNode rotateRight(ListNode head, int k) {
        // 1. The number of nodes in the list is in the range [0, 500].
        // 2. -100 <= Node.val <= 100
        // 3. 0 <= k <= 2 * 10^9        
        if (head == null) return head;
        //assert head != null;
        int size = 1;
        ListNode tail2;
        for (tail2 = head; tail2.next != null ; tail2 = tail2.next) {
            size++;
        }
        //assert tail2 != null;
        k %= size; // size = 3, k = 4 -> k = 1.
        if (k == 0) return head;

        ListNode tail1 = head;
        // k = 1, size = 3, prefixCount = 2.先頭2ノード。
        for (int prefixCount = size - k - 1; ; ) {
            if (prefixCount > 0) {
                prefixCount--;
                tail1 = tail1.next;
            } else {
                break;
            }
        }
        //assert tail1 != null;
        ListNode newHead = tail1.next;
        tail1.next = null;
        tail2.next = head;
        return newHead;
    }
}
