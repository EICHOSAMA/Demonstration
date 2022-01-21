package per.eicho.demo.leetcode.q2001_2100;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>2058. Find the Minimum and Maximum Number of Nodes Between Critical Points 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/">2058. Find the Minimum and Maximum Number of Nodes Between Critical Points</a>
 */
public final class Q2058 {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode previousNode = null;
        
        int index = 0;
        ListNode cursor = head;

        int firstCPIndex = -1;
        int previousCPIndex = -1;

        while (cursor.next != null) {
            if (isCriticalPoint(cursor, previousNode)) {
                if (firstCPIndex == -1) {
                    firstCPIndex = index;
                } else {
                    maxDistance = index - firstCPIndex;
                    minDistance = Math.min(minDistance, index - previousCPIndex);
                }

                previousCPIndex = index;
            }

            // move cursor
            index++;
            previousNode = cursor;
            cursor = cursor.next;
        }

        return new int[]{minDistance == Integer.MAX_VALUE ? -1 : minDistance, maxDistance};
    }

    private boolean isCriticalPoint(ListNode node, ListNode previousNode) {
        if (previousNode == null || node.next == null) return false;
        if (node.val < previousNode.val && node.val < node.next.val) return true;
        if (node.val > previousNode.val && node.val > node.next.val) return true;
        return false;
    }
}
