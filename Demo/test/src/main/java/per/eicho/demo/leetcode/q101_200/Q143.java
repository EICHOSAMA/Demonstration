package per.eicho.demo.leetcode.q101_200;

import java.util.Deque;
import java.util.LinkedList;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>143. Reorder List 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/reorder-list/">143. Reorder List</a>
 */
public final class Q143 {
    public void reorderList(ListNode head) {
        // 1. The number of nodes in the list is in the range [1, 5 * 10^4].
        // 2.1 <= Node.val <= 1000
        Deque<ListNode> deque = new LinkedList<>();

        // 1. add all nodes to deque.
        ListNode cursor = head;
        while(cursor != null) {
            deque.add(cursor);
            cursor = cursor.next;
        }

        // 2. reconstruct linked list
        cursor = deque.pollFirst();
        boolean getFirst = false;
        while(deque.size() > 0) {
            final ListNode node = getFirst ? deque.pollFirst() : deque.pollLast();
            getFirst ^= true;
            cursor.next = node;
            cursor = node;
        }
        cursor.next = null;
    }
}
