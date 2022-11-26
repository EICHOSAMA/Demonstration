package per.eicho.demo.leetcode_cn.offer;

import per.eicho.demo.leetcode.datastructure.ListNode;

/**
 * <p>剑指 Offer 24. 反转链表 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/">
 *   剑指 Offer 24. 反转链表</a>
 */
public final class Offer24 {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cursor = head;

        while (cursor != null) {
            final ListNode temp = cursor.next;
            cursor.next = prev;
            cursor = temp;
            prev = cursor;
        }

        return prev;
    }
}
