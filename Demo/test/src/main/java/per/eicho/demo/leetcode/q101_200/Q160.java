package per.eicho.demo.leetcode.q101_200;

import java.util.HashSet;
import java.util.Set;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>160. Intersection of Two Linked Lists 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/">160. Intersection of Two Linked Lists</a>
 */
public final class Q160 {

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode cursorA = headA, cursorB = headB;
        while (cursorA != cursorB) {
            cursorA = cursorA == null ? headB : cursorA.next;
            cursorB = cursorB == null ? headA : cursorB.next;
        }
        return cursorA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 1. The number of nodes of listA is in the m.
        // 2. The number of nodes of listB is in the n.
        // 3. 1 <= m, n <= 3 * 10^4
        // 4. 1 <= Node.val <= 10^5
        // 5. 0 <= skipA < m
        // 6. 0 <= skipB < n
        // 7. intersectVal is 0 if listA and listB do not intersect.
        // 8. intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.

        final Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.add(headB) == false)
                return headB;
            headB = headB.next;
        }
        return null;
    }
}
