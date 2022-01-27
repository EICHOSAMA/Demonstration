package per.eicho.demo.leetcode.q001_100;

import java.util.PriorityQueue;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>23. Merge k Sorted Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/merge-k-sorted-lists/">23. Merge k Sorted Lists</a>
 */
public final class Q23 {
    public ListNode mergeKLists(ListNode[] lists) {
        // 1. k == lists.length
        // 2. 0 <= k <= 10^4
        // 3. 0 <= lists[i].length <= 500
        // 4. -10^4 <= lists[i][j] <= 10^4
        // 5. lists[i] is sorted in ascending order.
        // 6. The sum of lists[i].length won't exceed 10^4.
        if (lists == null || lists.length == 0) return null;

        final ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cursor = dummy;

        final PriorityQueue<ListNode> heap = new PriorityQueue<>((n1, n2) -> {
            return Integer.compare(n1.val, n2.val);
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) heap.add(lists[i]);
        }

        while (heap.size() != 0) {
            final ListNode node = heap.poll();
            cursor.next = node;
            cursor = cursor.next;
            if (cursor.next != null) {
                heap.add(cursor.next);
            }
        }

        return dummy.next;
    }
}
